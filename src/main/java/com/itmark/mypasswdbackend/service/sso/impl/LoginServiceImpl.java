package com.itmark.mypasswdbackend.service.sso.impl;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import com.itmark.mypasswdbackend.consts.MarkTimeConstants;
import com.itmark.mypasswdbackend.consts.SysConstant;
import com.itmark.mypasswdbackend.entity.sso.MarkUser;
import com.itmark.mypasswdbackend.entity.sso.MyLoginUser;
import com.itmark.mypasswdbackend.service.sso.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 登录服务impl
 *
 * @author xiaoma
 * @date 2023/05/14
 */
@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Value("${jwt.secret}")
    private String key;

    @Override
    public String login(MarkUser user) {
        //1获取AuthenticationManager（接口） ProviderManager（实现类）authenticate方法进行认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getUserPassword());
        MyLoginUser myLoginUser = null;
        try {
            Authentication authenticate = authenticationManager.authenticate(authenticationToken);
            //2认证不通过抛出异常给出提示(没啥用)已经在上一步验证并抛出异常了
            myLoginUser = (MyLoginUser) authenticate.getPrincipal();
        } catch (AuthenticationException e) {
            throw new UsernameNotFoundException("用户名或密码错误！请检查！");
        }
        if (Objects.isNull(myLoginUser)){
            //2.该提示可以防止测试用户不存在
            throw new UsernameNotFoundException("用户名或密码错误！请检查！");
        }
        //2认证通过返回jwt
        DateTime now = DateTime.now();
        DateTime newTime = now.offsetNew(DateField.MINUTE, 30);
        Map<String,Object> payload = new HashMap<String,Object>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT, now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT, newTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE, now);
        //载荷
        payload.put(SysConstant.KEY_ID, Long.valueOf(myLoginUser.getUser().getUserId()));
        String token = JWTUtil.createToken(payload, key.getBytes());
        return token;
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyLoginUser loginUser = (MyLoginUser)authentication.getPrincipal();
        Long userId = loginUser.getUser().getUserId();
        // 根据用户id进行一些登出前置操作，如删除redis中的用户信息

    }

    @Override
    public String refreshTokenIfNeed(String token,MarkUser user){
        SimpleDateFormat sdf = new SimpleDateFormat(MarkTimeConstants.FULL_TIME_WITH_SECOND_ONE);
        JWT jwt = JWTUtil.parseToken(token);
        JWTPayload payload = jwt.getPayload();
        //  时间戳基本单位是秒，SimpleDateFormat是毫秒
        Integer dateTimeExpInteger = (Integer)payload.getClaim(JWTPayload.EXPIRES_AT);
        Integer dateTimeIssInteger = (Integer)payload.getClaim(JWTPayload.ISSUED_AT);
        Date dateTimeExp =null ;
        Date dateTimeIss = null;
        try {
            dateTimeExp = DateUtil.date(dateTimeExpInteger.longValue()*1000L);
            dateTimeIss = sdf.parse(sdf.format(dateTimeIssInteger*1000L));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Date nowDate=new Date();
        long betweenMinute = DateUtil.between(nowDate, dateTimeExp, DateUnit.MINUTE);
        Long needRefreshMinute = 30L-betweenMinute;
        DateTime newDateExp = DateUtil.offset(nowDate, DateField.MINUTE, needRefreshMinute.intValue());;
        Map<String,Object> newPayload = new HashMap<String,Object>();
        //签发时间
        newPayload.put(JWTPayload.ISSUED_AT, nowDate);
        //过期时间
        newPayload.put(JWTPayload.EXPIRES_AT, newDateExp);
        //生效时间
        newPayload.put(JWTPayload.NOT_BEFORE, nowDate);
        //载荷
        newPayload.put(SysConstant.KEY_ID, Long.valueOf(user.getUserId()));
        // 如果令牌已经使用了10分钟，那么可以颁发一个新的令牌否则不颁发
        if (!(needRefreshMinute >10L)){
            return null;
        }
        String newToken = JWTUtil.createToken(newPayload, key.getBytes());
        log.info("旧令牌颁发时间为：{}，过期时间为：{}，剩余有效时间为：{}分钟。新令牌过期时间为：{}",sdf.format(dateTimeIss),sdf.format(dateTimeExp),betweenMinute,sdf.format(newDateExp));
        return newToken;
    }

    @Override
    public String mockBCryptPasswordEncoder(String mockPass) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(mockPass);
        return encodedPassword;
    }
}
