package com.itmark.mypasswdbackend;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@MapperScan(basePackages = "com.itmark.mapper")
@EnableAsync
@EnableCaching
// 开启访问目标资源前认证
@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
public class MyPasswdBackendApplication {

    public static void main(String[] args) {
        LogFactory.useCustomLogging(StdOutImpl.class);
        SpringApplication.run(MyPasswdBackendApplication.class, args);
    }

}
