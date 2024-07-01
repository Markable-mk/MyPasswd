package com.itmark.mypasswdbackend.service.demo.liteflow.impl;

import com.itmark.mypasswdbackend.service.demo.liteflow.LiteFlowDemoService;
import com.yomahub.liteflow.core.FlowExecutor;
import com.yomahub.liteflow.flow.LiteflowResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/1 15:37
 */
@Service
public class LiteFlowDemoServiceImpl implements LiteFlowDemoService {

    @Resource
    private FlowExecutor flowExecutor;

    @Override
    public void doLiteFlowDemoOne() {
        LiteflowResponse response = flowExecutor.execute2Resp("chain1", "arg");
    }

}
