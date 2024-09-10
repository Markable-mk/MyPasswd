package com.itmark.mypasswdbackend.aspect;

import java.lang.reflect.Method;

import com.itmark.mypasswdbackend.anno.RequestLock;
import com.itmark.mypasswdbackend.util.sys.RequestKeyGenerator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.util.StringUtils;

/**
 * @description 缓存实现
 */
@Aspect
@Configuration
@Order(2)
public class RedisRequestLockAspect {

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisRequestLockAspect(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Around("execution(public * * (..)) && @annotation(com.itmark.mypasswdbackend.anno.RequestLock)")
    public Object interceptor(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature)joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        RequestLock requestLock = method.getAnnotation(RequestLock.class);
        if (StringUtils.isEmpty(requestLock.prefix())) {
            //throw new BizException(ResponseCodeEnum.BIZ_CHECK_FAIL, "重复提交前缀不能为空");
        }
        // 获取自定义key
        final String lockKey = RequestKeyGenerator.getLockKey(joinPoint);
        // 使用RedisCallback接口执行set命令，设置锁键；设置额外选项：过期时间和SET_IF_ABSENT选项
        final Boolean success = stringRedisTemplate.execute(
            (RedisCallback<Boolean>)connection -> connection.set(lockKey.getBytes(), new byte[0],
                Expiration.from(requestLock.expire(), requestLock.timeUnit()),
                RedisStringCommands.SetOption.SET_IF_ABSENT));
        if (!success) {
            // throw new (ResponseCodeEnum.BIZ_CHECK_FAIL, "您的操作太快了,请稍后重试");
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
