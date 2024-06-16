package com.itmark.mypasswdbackend.filter;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.itmark.mypasswdbackend.anno.NoNeedAuthorization;
import com.itmark.mypasswdbackend.consts.SysConstant;
import com.itmark.mypasswdbackend.entity.sso.MyLoginUser;
import com.itmark.mypasswdbackend.entity.sso.MarkUser;
import com.itmark.mypasswdbackend.enums.RequestMethodEnum;
import com.itmark.mypasswdbackend.mapper.sso.UserDetailsMapper;
import com.itmark.mypasswdbackend.util.web.IpUtil;
import com.itmark.mypasswdbackend.util.web.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;



/**
 * 1 在这里主要做的是一个授权的操作
 * 1.1 如果不携带token则虚拟一个游客账户，并给予游客权限授权放行
 * 1.2 如果携带token则尝试解析token 解析成功则授权放行
 * 1.3 如果携带成功并解析token异常 这里可以试一下直接返回提示看看能不能行（不行也放行，到拦截器会再次判断jwt）
 */

@Slf4j
@Component
@SuppressWarnings("all")
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String key;

    @Autowired
    private UserDetailsMapper userDetailsMapper;

    @Autowired
    @Qualifier("stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 定义维护一个可以匿名访问的接口列表（路径参数）
     * 其他普通接口通过注解进行了维护（socket手动维护了）
     */
    public static List<String> PATH_PARAM_REQUEST_PERMITALL = Arrays.asList(
            "GET /textContent/articleDateArchive/**",
            "GET /markTag/**",
            "GET /textContent/articleDateArchiveGraphBar/**",
            "GET /favicon.ico",
            "GET /websocket/**"
    );

    /**
     * 异常请求集合
     */
    public static List<String> INVALID_URI_LIST =  Arrays.asList(
            "/stacks",
            "/js",
            "src",
            "html",
            "txt",
            "xml",
            "apps",
            "/application",
            "/cluster"
    );

    /**
     * 异常请求集合
     */
    public static List<String> INVALID_IP_LIST =  Arrays.asList(
            "env",
            "ndi",
            "dap"
    );
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("=================filter request start===========================");
        String ipAddr = IpUtil.getIpAddr();
        log.info("请求IP地址：{}",ipAddr);
        String requestURI = httpServletRequest.getRequestURI();
        String method = httpServletRequest.getMethod();
        String token = httpServletRequest.getHeader("Authorization");
        log.info("request uri:{}",requestURI);
        log.info("request method:{}",method);
        log.info("token:{}", token);
        // 根据请求方法还有uri判断是否需要进行登录
        log.info("=================filter request end===========================");

        // 验证访问的资源是否安全
        if(this.judgeUrlValid(requestURI)||this.judgeIpValid(ipAddr)){
             httpServletRequest.setAttribute("errorCode", "INVALID_REQUEST");
             httpServletRequest.getRequestDispatcher("/error/login-auth-fail").forward(httpServletRequest, httpServletResponse);
             return;
        }

        Set<String> urlsSet =this.getAllanoymousSet();
        // 1逻辑一：登陆接口，匿名接口直接放行不走下面用户验证逻辑
        String myRequestUri = method + " " + requestURI;
        if (urlsSet.contains(myRequestUri)|| WebUtils.match(PATH_PARAM_REQUEST_PERMITALL,myRequestUri)){
            filterChain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }
        // 2逻辑二：对于没有token的请求，前面又不再匿名请求路径内，这里应该给出提示去进行登陆
        if (StringUtils.isEmpty(token)){
            httpServletRequest.setAttribute("errorCode", "TOKEN_NOT_EXISTS");
            httpServletRequest.getRequestDispatcher("/error/login-auth-fail").forward(httpServletRequest, httpServletResponse);
            return;
        }
        JWT jwt = JWTUtil.parseToken(token);
        // 3逻辑三：对于有token的要校验token的合法性（是否可以正常解析、是否过期）
        // 3.1验证是否合法
        if (!JWTUtil.verify(token, key.getBytes())){
            httpServletRequest.setAttribute("errorCode", "TOKEN_INVALID");
            httpServletRequest.getRequestDispatcher("/error/login-auth-fail").forward(httpServletRequest, httpServletResponse);
            return;
        }
        // 3.2验证是否过期
        if (!jwt.setKey(key.getBytes()).validate(0)){
            httpServletRequest.setAttribute("errorCode", "TOKEN_EXPIRED");
            httpServletRequest.getRequestDispatcher("/error/login-auth-fail").forward(httpServletRequest, httpServletResponse);
            return;
        }
        JWTPayload payload = jwt.getPayload();
        String sid = String.valueOf(payload.getClaim("id"));
        // 4逻辑4：查询缓存或者数据库中的用户信息权限信息
        MarkUser user = userDetailsMapper.selUserByUserId(Long.parseLong(sid));
        // 判断用户是否为null
        if (Objects.isNull(user)){
            httpServletRequest.setAttribute("errorCode", "TOKEN_EXPIRED");
            httpServletRequest.getRequestDispatcher("/error/login-auth-fail").forward(httpServletRequest, httpServletResponse);
            return;
        }
        // 查询用户权限 这里不查其实也没有关系，反正使用了自定义的资源权限校验器
        List<String> permitMenuList = user.getPermitMenuList();
        MyLoginUser myLoginUser = new MyLoginUser(user,permitMenuList);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(myLoginUser,null,myLoginUser.getAuthorities() );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest,httpServletResponse);
    }

    /**
     * 判断IP中是否存在注入字符串
     * @param requestIp
     * @return
     */
    private boolean judgeIpValid(String requestIp) {
        for (String sip : INVALID_IP_LIST) {
            if (requestIp.toUpperCase(Locale.ROOT).contains(sip.toUpperCase(Locale.ROOT))){
                return true;
            }
        }
        return false;
    }

    /**
     * 判断uri是否合法
     * @param requestURI
     * @return
     */
    private boolean judgeUrlValid(String requestURI) {
        for (String surl : INVALID_URI_LIST) {
            if (requestURI.toUpperCase(Locale.ROOT).contains(surl.toUpperCase(Locale.ROOT))){
                return true;
            }
        }

        return false;
    }

    /**
     * 把所有匿名接口以及方法放到一个集合
     * @return
     */
    private Set<String> getAllanoymousSet() {
        // 防止出现内存数据丢失造成的空指针异常
        if (!org.springframework.util.StringUtils.hasLength(redisTemplate.opsForValue().get(SysConstant.ANONYMOUSURLS_KEY))){
            getAppAnoymousSet();
        }
        String uriStrings = redisTemplate.opsForValue().get(SysConstant.ANONYMOUSURLS_KEY);
        Map map = JSON.parseObject(uriStrings, Map.class);
        Set<String> requestType = (Set<String>)map.keySet();
        Set<String> result = new HashSet<>();
        for (String type : requestType) {
            JSONArray o = (JSONArray)map.get(type);
            List<String> urls = o.toJavaList(String.class);
            for (String url : urls) {
                result.add(type+" "+ url);
            }
        }
        return result;
    }

    /**
     * 重置内存中项目的匿名访问资源路径
     */
    private void getAppAnoymousSet() {
        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        Map<RequestMappingInfo, HandlerMethod> handlerMethodMap  = requestMappingHandlerMapping.getHandlerMethods();
        Map<String, Set<String>> anonymousUrls = new HashMap<>(6);
        Set<String> get = new HashSet<>();
        Set<String> post = new HashSet<>();
        Set<String> put = new HashSet<>();
        Set<String> patch = new HashSet<>();
        Set<String> delete = new HashSet<>();
        Set<String> all = new HashSet<>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> infoEntry : handlerMethodMap.entrySet()) {
            HandlerMethod handlerMethod = infoEntry.getValue();
            NoNeedAuthorization anonymousAccess = handlerMethod.getMethodAnnotation(NoNeedAuthorization.class);
            if (null != anonymousAccess) {
                List<RequestMethod> requestMethods = new ArrayList<>(infoEntry.getKey().getMethodsCondition().getMethods());
                RequestMethodEnum request = RequestMethodEnum.find(requestMethods.size() == 0 ? RequestMethodEnum.ALL.getType() : requestMethods.get(0).name());
                switch (Objects.requireNonNull(request)) {
                    case GET:
                        get.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                        break;
                    case POST:
                        post.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                        break;
                    case PUT:
                        put.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                        break;
                    case PATCH:
                        patch.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                        break;
                    case DELETE:
                        delete.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                        break;
                    default:
                        all.addAll(infoEntry.getKey().getPatternsCondition().getPatterns());
                        break;
                }
            }
        }
        anonymousUrls.put(RequestMethodEnum.GET.getType(), get);
        anonymousUrls.put(RequestMethodEnum.POST.getType(), post);
        anonymousUrls.put(RequestMethodEnum.PUT.getType(), put);
        anonymousUrls.put(RequestMethodEnum.PATCH.getType(), patch);
        anonymousUrls.put(RequestMethodEnum.DELETE.getType(), delete);
        anonymousUrls.put(RequestMethodEnum.ALL.getType(), all);
        String jsonStringMap = JSON.toJSONString(anonymousUrls);
        redisTemplate.opsForValue().set(SysConstant.ANONYMOUSURLS_KEY,jsonStringMap);
    }


}
