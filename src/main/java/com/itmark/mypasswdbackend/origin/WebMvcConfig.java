package com.itmark.mypasswdbackend.origin;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*
跨域问题全局处理，注册拦截器(由于spring-security的加入，这里不再使用拦截器)
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer{


    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .exposedHeaders("new-Authorization");
            }
        };
    }

}