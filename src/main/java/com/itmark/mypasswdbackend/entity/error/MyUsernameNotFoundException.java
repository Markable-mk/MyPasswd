package com.itmark.mypasswdbackend.entity.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/6/15 7:50
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MyUsernameNotFoundException  extends RuntimeException{

    /**
     * 自定义异常信息
     */
    private String message;

}
