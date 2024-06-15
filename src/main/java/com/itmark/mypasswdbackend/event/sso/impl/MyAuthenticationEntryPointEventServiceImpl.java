package com.itmark.mypasswdbackend.event.sso.impl;

import com.itmark.mypasswdbackend.entity.error.UserNameOrPasswdNotRightError;
import com.itmark.mypasswdbackend.entity.event.MyAuthenticationEntryPointEvent;
import com.itmark.mypasswdbackend.event.sso.MyAuthenticationEntryPointEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/6/15 22:48
 */

@Slf4j
@Service
public class MyAuthenticationEntryPointEventServiceImpl implements MyAuthenticationEntryPointEventService {

    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Override
    public void prepareOneEvent(String code, String desc) {
        MyAuthenticationEntryPointEvent myEvent = new MyAuthenticationEntryPointEvent(eventPublisher,code,desc);
        eventPublisher.publishEvent(myEvent);
    }

    @EventListener
    public void handleForPersonSaveEvent(MyAuthenticationEntryPointEvent myEvent){
        log.info("myEvent -> {}", myEvent);
        throw new UserNameOrPasswdNotRightError("myEvent");
    }
}
