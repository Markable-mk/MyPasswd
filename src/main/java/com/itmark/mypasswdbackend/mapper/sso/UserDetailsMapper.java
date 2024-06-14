package com.itmark.mypasswdbackend.mapper.sso;

import com.itmark.mypasswdbackend.entity.sso.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户详细信息映射器
 *
 * @author xiaoma
 * @date 2023/05/14
 */
@Mapper
public interface UserDetailsMapper {
    /**
     * 选取用户用户名
     *
     * @param userName 用户名
     * @return {@link User}
     */
    User selUserByUserName(@Param("userName") String userName);
    User selUserByUserId(@Param("userId") Long userId);
}
