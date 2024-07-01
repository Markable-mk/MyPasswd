package com.itmark.mypasswdbackend.component.liteflow.demo;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("c")
public class ThreeCmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        log.info("正在处理c组件业务");
    }
}
