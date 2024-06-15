package com.itmark.mypasswdbackend.error;

import com.itmark.mypasswdbackend.entity.error.InvalidRequestUriError;
import com.itmark.mypasswdbackend.entity.error.UserNameOrPasswdNotRightError;
import com.itmark.mypasswdbackend.entity.error.LogintTokenError;
import com.itmark.mypasswdbackend.entity.resp.MarkAppRespEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description: 统一异常处理
 * @Author: 马宽
 * @Date: 2022/9/3 22:22
 */

//控制器增强
@ControllerAdvice
@Slf4j
public class MyExceptionHandler {

    //和先后顺序无关//对这类异常进行处理
    @ExceptionHandler(LogintTokenError.class)
    //正文返回而不是页面跳转
    @ResponseBody
    public MarkAppRespEntity myExceptionHandler2(Exception e) {
        //2.记录日志
        log.error("my sys login catch exception:{}", e.getMessage());
        //3.返回通用格式
        return MarkAppRespEntity.error().status(40001).message(e.getMessage());
    }

    //请求类型不正确
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    //正文返回而不是页面跳转
    @ResponseBody
    public MarkAppRespEntity myExceptionHandler1(Exception e) {
        //2.记录日志
        log.error("请求类型异常请检查:{}", e.getMessage());
        //3.返回通用格式
        return MarkAppRespEntity.error().status(50001).message("接口请求类型异常请检查");
    }


    @ExceptionHandler(InvalidRequestUriError.class)
    //正文返回而不是页面跳转
    @ResponseBody
    public MarkAppRespEntity myExceptionHandler3(Exception e) {
        //2.记录日志
        log.error("访问存在安全风险，系统限制访问此类型资源:{}", e.getMessage());
        //3.返回通用格式
        return MarkAppRespEntity.error().status(60001).message("访问存在安全风险，系统限制访问此类型资源");
    }


    @ExceptionHandler(UserNameOrPasswdNotRightError.class)
    @ResponseBody
    public MarkAppRespEntity myExceptionHandler4(Exception e) {
        //3.返回通用格式
        return MarkAppRespEntity.error().status(40001).message("用户名或者密码不正确，请检查！");
    }


}
