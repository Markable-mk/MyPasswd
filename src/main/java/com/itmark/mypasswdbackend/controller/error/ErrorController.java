package com.itmark.mypasswdbackend.controller.error;

import com.itmark.mypasswdbackend.entity.error.InvalidRequestUriError;
import com.itmark.mypasswdbackend.entity.error.LogintTokenError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 用于记录过滤器中的异常
 * @author: makuan
 * @date: 2023/5/16 18:51
 */

@Slf4j
@RestController
@RequestMapping("/error")
public class ErrorController {
    @RequestMapping("/login-auth-fail")
    public void loginAuthFail(HttpServletRequest request) throws Exception {
        // 此处构造一个合适的异常并抛出即可
        String errorCode = (String)request.getAttribute("errorCode");
        if (errorCode.equals("TOKEN_NOT_EXISTS")){
            throw new LogintTokenError("请先进行登录！");
        }
        if (errorCode.equals("TOKEN_EXPIRED")){
            throw new LogintTokenError("您的登录信息已过期请重新登录");
        }
        if (errorCode.equals("TOKEN_INVALID")){
            throw new LogintTokenError("您的登录信息存在安全风险请重新登录");
        }
        if (errorCode.equals("INVALID_REQUEST")){
            throw new InvalidRequestUriError("您的访问存在安全风险，系统限制访问此类型资源");
        }
        log.error("认证过滤异常捕获：{}",errorCode);
    }
}
