package com.itmark.mypasswdbackend.service.sso;


import com.itmark.mypasswdbackend.entity.sso.MarkUser;

/**
 * 登录服务
 *
 * @author xiaoma
 * @date 2023/05/14
 */
public interface LoginService {
    /**
     * 1登录
     *
     */
    String login(MarkUser user);

    /**
     * 2登出
     */

    void logout();

    /**
     * 3 token校验根据token获取用户信息
     * @param token
     * @param user
     * @return
     */
    String refreshTokenIfNeed(String token,MarkUser user);

    /**
     * 4 模拟密码
     * @param mockPass
     * @return
     */
    String mockBCryptPasswordEncoder(String mockPass);
}
