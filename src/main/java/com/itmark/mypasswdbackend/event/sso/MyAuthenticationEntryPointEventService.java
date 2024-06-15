package com.itmark.mypasswdbackend.event.sso;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/6/15 22:47
 */
public interface MyAuthenticationEntryPointEventService {
    /**
     * 接收登陆认证过程中需要抛出的异常信息并抛出异常给全局异常返回
     * @param code
     * @param desc
     */
   void prepareOneEvent(String code,String desc);
}
