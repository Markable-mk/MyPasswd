package com.itmark.mypasswdbackend.security;

import com.alibaba.fastjson.JSON;
import com.itmark.mypasswdbackend.entity.resp.MarkAppRespEntity;
import com.itmark.mypasswdbackend.util.web.WebUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class MyAuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)  {
        log.info("捕获到认证异常：{}",e.getMessage());
        MarkAppRespEntity<String> message = MarkAppRespEntity.alert("").status(40001).message("用户认证失败请重新登录-SECURITY-AUTHENTICATIONENTRYPOINT");
        // 提示授权失败
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(message));
    }
}
