package com.itmark.mypasswdbackend.entity.event;

import com.itmark.mypasswdbackend.consts.SysConstant;
import lombok.Getter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 自定义事件
 * @author: makuan
 * @date: 2023/11/15 11:18
 */
@Getter
@ToString
public class MyAuthenticationEntryPointEvent extends ApplicationEvent {

    private Map<String,String> eventMap= new HashMap<>();

    public MyAuthenticationEntryPointEvent(Object source, String code, String desc) {
        super(source);
        this.eventMap.put(SysConstant.KEY_DESC,desc);
        this.eventMap.put(SysConstant.KEY_CODE,code);
    }

}
