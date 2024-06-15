package com.itmark.mypasswdbackend.entity.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description: 通常是用户名没有找到，或者密码不正确抛出
 * @Author: 马宽
 * @Date: 2022/9/3 22:28
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserNameOrPasswdNotRightError extends RuntimeException{

    /**
     * 自定义异常信息
     */
    private String message;

}
