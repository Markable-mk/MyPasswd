package com.itmark.mypasswdbackend.service.security;


import com.itmark.mypasswdbackend.entity.error.MyUsernameNotFoundException;
import com.itmark.mypasswdbackend.entity.sso.MyLoginUser;
import com.itmark.mypasswdbackend.entity.sso.MarkUser;
import com.itmark.mypasswdbackend.mapper.sso.UserDetailsMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;


/**
 * 我用户详细信息服务impl
 *
 * @author xiaoma
 * @date 2023/05/14
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    /**
     * 用户详细信息映射器
     */
    @Resource
    private UserDetailsMapper userDetailsMapper;

    /**
     * 加载用户by用户名
     *
     * @param username 用户名
     * @return {@link UserDetails}
     * @throws UsernameNotFoundException 用户名没有发现异常
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //1.根据用户名去查询用户密码和加密验
        MarkUser user = userDetailsMapper.selUserByUserName(username);
        if (Objects.isNull(user)){
            //2.该提示可以防止测试用户不存在
            // throw new UsernameNotFoundException("用户名或密码错误！-SERCURITY-USERDETAILS"); -- AuthenticationEntryPoint 感知不到 需要使用RuntimeException
            throw new MyUsernameNotFoundException("用户名或密码错误！-SERCURITY-USERDETAILS");
        }
        // 3.查询权限 该步骤省略，这里不需要查，只用于颁发token,权限在过滤器中进行了查询
        //UserDetails
        return new MyLoginUser(user,null);
    }
}
