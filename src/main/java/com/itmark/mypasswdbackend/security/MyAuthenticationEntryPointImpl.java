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

    /**
     * 触发实时机1：密码对比失败、用户名没有找到
     * @param httpServletRequest
     * @param httpServletResponse
     * @param e
     */
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e)  {
        log.info("自定义认证失败处理器捕获到认证异常：{}",e.getMessage());
        MarkAppRespEntity<String> message = MarkAppRespEntity.alert("").status(40001).message(e.getMessage());
        // 提示授权失败
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(message));
    }
}
