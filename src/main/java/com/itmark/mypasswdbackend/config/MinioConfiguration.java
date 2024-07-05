package com.itmark.mypasswdbackend.config;

import io.minio.MinioClient;
import io.minio.errors.InvalidEndpointException;
import io.minio.errors.InvalidPortException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * minio 配置
 */
@Data
@Configuration
public class MinioConfiguration {

    /**
     * minio 服务地址
     */
    @Value("${minio.url}")
    private String url;

    /**
     * 用户
     */
    @Value("${minio.username}")
    private String accessKey;

    /**
     * 密码
     */
    @Value("${minio.password}")
    private String secretKey;

    @Bean
    MinioClient minioClient() throws InvalidEndpointException, InvalidPortException {
        return new MinioClient(
                this.getUrl(),
                this.getAccessKey(),
                this.getSecretKey()
        );
    }

}
