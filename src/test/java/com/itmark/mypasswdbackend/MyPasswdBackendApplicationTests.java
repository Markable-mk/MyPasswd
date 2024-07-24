package com.itmark.mypasswdbackend;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
//获取启动类，加载配置，寻找主配置启动类（被 @SpringBootApplication 注解的）
@SpringBootTest
//让JUnit运行Spring的测试环境,获得Spring环境的上下文的支持
@RunWith(SpringRunner.class)
class MyPasswdBackendApplicationTests {

    @Test
    void contextLoads() {
        log.info("单元测试执行了");
    }

}
