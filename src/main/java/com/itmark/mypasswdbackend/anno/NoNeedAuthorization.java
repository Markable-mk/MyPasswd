package com.itmark.mypasswdbackend.anno;

import java.lang.annotation.*;

/**
 * @Description: 无需token校验注解
 * @Author: 马宽
 * @Date: 2022/9/3 22:44
 */

//判断是否包含@NoAuthorization注解，如果包含，直接放行
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented //本质接口，作用：标记
public @interface NoNeedAuthorization {
}
