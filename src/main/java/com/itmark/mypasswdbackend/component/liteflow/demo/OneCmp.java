package com.itmark.mypasswdbackend.component.liteflow.demo;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("a")
public class OneCmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        log.info("正在处理a组件业务");
    }
}
