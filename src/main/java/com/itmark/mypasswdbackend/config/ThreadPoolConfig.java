package com.itmark.mypasswdbackend.config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ThreadPoolConfig {

    /**
     * 线程池核心线程数
     **/
    @Value("${dac.executorPool.coreSize:#{T(java.lang.Runtime).getRuntime().availableProcessors()}}")
    private int coreSize;
    /**
     * 线程池核心线程数
     **/
    @Value("${dac.executorPool.queueSize:9999999}")
    private int queueSize;

    /**
     * dac服务线程池
     **/
    @Bean("executorPool")
    public ThreadPoolExecutor createExecutorPool() {
        return new ThreadPoolExecutor(coreSize,
                coreSize * 2 + 1,
                30,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(queueSize),
                Thread::new,
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

}
