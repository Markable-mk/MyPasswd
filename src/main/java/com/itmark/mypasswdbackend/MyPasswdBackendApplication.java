package com.itmark.mypasswdbackend;

import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.bootstrap.ServerBootstrap;

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
public class MyPasswdBackendApplication  implements CommandLineRunner {

    @Autowired
    private ServerBootstrap serverBootstrap;

    public static void main(String[] args) {
        LogFactory.useCustomLogging(StdOutImpl.class);
        SpringApplication.run(MyPasswdBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        ChannelFuture future = serverBootstrap.bind(6666).sync();
        future.addListener((ChannelFutureListener) futureInit -> {
            if (futureInit.isSuccess()) {
                System.out.println("Netty server started on port 8080");
            } else {
                System.err.println("Failed to start Netty server");
                futureInit.cause().printStackTrace();
            }
        });
        future.channel().closeFuture().sync();
    }
}
