package com.itmark.mypasswdbackend.entity.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @description:
 * @author: makuan
 * @date: 2023/6/4 13:21
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class InvalidRequestUriError extends RuntimeException{

    /**
     * 自定义异常信息
     */
    private String message;

}
