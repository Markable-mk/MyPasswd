package com.itmark.mypasswdbackend.controller.demo.daily;

import com.itmark.mypasswdbackend.service.demo.daily.DailyService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/1 10:42
 */

@RestController
@RequestMapping("/demo/hutool")
public class DailyController {
    @Resource
    private DailyService dailyService;


}
