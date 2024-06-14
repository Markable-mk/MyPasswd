package com.itmark.mypasswdbackend.config;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @功能说明 redisCache 序列化和过期时间配置
 * @Author makuan
 * @Date 2023-02-16
 */
@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport {

    // 缓存过期时间 单位 ： H
    final Integer EXCEPTION_INFO_TTL = 24*30;

    /**
     * 二进制转成json 数据
     * 配置文件的配置没有用上
     *
     * @return config
     */
    @Bean
    public RedisCacheConfiguration redisCacheConfiguration() {
 
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();
        //过期时间
        //config = config.entryTtl(Duration.ofHours(EXCEPTION_INFO_TTL));
        //序列化
        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        //反序列化
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
        return config;
    }

    @Bean(name = "stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }
 
}