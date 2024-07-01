package com.itmark.mypasswdbackend.controller.demo.liteflow;

import com.itmark.mypasswdbackend.service.demo.liteflow.LiteFlowDemoService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/1 15:36
 */
@RestController
@RequestMapping("/demo/lite-flow")
public class LiteFlowController {

    @Resource
    private LiteFlowDemoService liteFlowDemoService;

    @GetMapping("/demo1")
    public void liteFlowDemoOne(){
        liteFlowDemoService.doLiteFlowDemoOne();
    }
}
