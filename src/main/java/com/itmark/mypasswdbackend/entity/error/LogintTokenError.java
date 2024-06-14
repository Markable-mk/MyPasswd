package com.itmark.mypasswdbackend.entity.error;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Description:
 * @Author: 马宽
 * @Date: 2022/9/3 22:28
 */

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LogintTokenError extends Exception{
    private String message;
}
