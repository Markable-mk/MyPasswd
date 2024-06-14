package com.itmark.mypasswdbackend.security;

import com.alibaba.fastjson.JSON;
import com.itmark.mypasswdbackend.entity.resp.MarkAppRespEntity;
import com.itmark.mypasswdbackend.util.web.WebUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对已认证用户无权限的处理
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        MarkAppRespEntity<String> message = MarkAppRespEntity.alert("").status(40002).message("权限认证失败请联系管理员授权-SECURITY-ACCESSDENIEDHANDLER");
        // 提示无权限  此处抛异常没有用，可以发布一个事件，交给事件接收方报一个异常
        WebUtils.renderString(httpServletResponse, JSON.toJSONString(message));
    }
}
