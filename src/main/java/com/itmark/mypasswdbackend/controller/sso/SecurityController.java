package com.itmark.mypasswdbackend.controller.sso;

import com.itmark.mypasswdbackend.anno.NoNeedAuthorization;
import com.itmark.mypasswdbackend.entity.resp.MarkAppRespEntity;
import com.itmark.mypasswdbackend.entity.sso.MarkUser;
import com.itmark.mypasswdbackend.entity.sso.MyLoginUser;
import com.itmark.mypasswdbackend.service.sso.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/sso-security")
public class SecurityController {

    @Autowired
    private LoginService loginService;
    /**
     * 1 普通登录通过用户名密码登录
     * @return
     */
    @NoNeedAuthorization
    @PostMapping("/login")
    public MarkAppRespEntity<String> securityLogin(@RequestBody MarkUser user){

        String jwt = loginService.login(user);

        return MarkAppRespEntity.success(jwt).status(200).message("登录成功！");
    }

    /**
     * 2 登出，前端需要同步删除本地存储中的token
     * @return
     */
    @PostMapping("/logout")
    public MarkAppRespEntity<String> securityLogout(){
        loginService.logout();
        return MarkAppRespEntity.success("").status(200).message("登出成功！");
    }

    /**
     * 3 token校验根据token获取用户信息
     *   本接口有两个功能：1查询用户信息，2如果token即将失效更新token
     * @return
     */
    @GetMapping("/getUserByToken")
    public MarkAppRespEntity<MarkUser> queryUserByToken(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> result = new HashMap<>();
        String authorization = request.getHeader("Authorization");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyLoginUser loginUser = (MyLoginUser)authentication.getPrincipal();
        MarkUser user = loginUser.getUser();
        String newToken=loginService.refreshTokenIfNeed(authorization,user);
        if (StringUtils.isNotEmpty(newToken)){
            response.setHeader("new-Authorization",newToken);
            // 对前端暴露此响应头
            //response.setHeader("Access-Control-Expose-Headers", "new-Authorization");
        }
        user.setUserPassword(null);
        return MarkAppRespEntity.success(user).message("获取用户信息成功");
    }

    @NoNeedAuthorization
    @GetMapping("/mockBCryptPasswordEncoder")
    public MarkAppRespEntity<String> mockBCryptPasswordEncoder(@RequestParam(name = "mockPass")String mockPass){
        String mock=loginService.mockBCryptPasswordEncoder(mockPass);
        return MarkAppRespEntity.success(mock).message("获取模拟BCryptPasswordEncoder成功！");
    }
}
