package com.itmark.mypasswdbackend.controller.demo.hu;

import com.itmark.mypasswdbackend.service.demo.hu.HuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/7/30 21:54
 */

@RestController
@RequestMapping("/demo/hutool")
public class HuToolController {

    @Resource
    private HuService huService;

    @GetMapping("/window")
    public void window(){
        huService.window();
    }

    @GetMapping("/dateUtil")
    public void dateUtil(){
        huService.dateUtil();
    }

    @GetMapping("/sysUtil")
    public void sysUtil(){
        huService.sysUtil();
    }

    @GetMapping("/pcUtil")
    public void pcUtil(){
        huService.pcUtil();
    }

    /**
     * 校验是否有中文
     * @param txt
     * @return
     */
    @GetMapping("/validatorHasChinese")
    public boolean validator(@RequestParam(name = "txt") String txt){
        return huService.validatorChinese(txt);
    }


    @GetMapping("/convert")
    public void convertUtil(){
        huService.convertUtil();
    }
}
