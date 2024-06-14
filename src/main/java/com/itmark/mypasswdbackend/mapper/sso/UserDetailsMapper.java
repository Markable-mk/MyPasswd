package com.itmark.mypasswdbackend.mapper.sso;

import com.itmark.mypasswdbackend.entity.sso.MarkUser;
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
     * 01选取用户用户名
     *
     * @param userName 用户名
     * @return {@link MarkUser}
     */
    MarkUser selUserByUserName(@Param("userName") String userName);

    /**
     * 02通过用户ID查询用户
     * @param userId
     * @return
     */
    MarkUser selUserByUserId(@Param("userId") Long userId);
}
