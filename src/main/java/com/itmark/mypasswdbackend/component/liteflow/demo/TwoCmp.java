package com.itmark.mypasswdbackend.component.liteflow.demo;

import com.yomahub.liteflow.core.NodeComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component("b")
public class TwoCmp extends NodeComponent {

    @Override
    public void process() {
        //do your business
        log.info("正在处理b组件业务");
    }
}
