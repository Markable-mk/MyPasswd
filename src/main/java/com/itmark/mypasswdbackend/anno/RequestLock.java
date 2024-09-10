package com.itmark.mypasswdbackend.anno;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @description 请求防抖锁，用于防止前端重复提交导致的错误
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface RequestLock {
    /**
     * redis锁前缀
     *
     * @return 默认为空，但不可为空
     */
    String prefix() default "";

    /**
     * redis锁过期时间
     *
     * @return 默认2秒
     */
    int expire() default 2;

    /**
     * redis锁过期时间单位
     *
     * @return 默认单位为秒
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;

    /**
     * redis  key分隔符
     *
     * @return 分隔符
     */
    String delimiter() default "&";
}
