package com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory;

import com.itmark.mypasswdbackend.entity.designpattern.factory.demo.Coffee;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:35
 */
public class SimpleLatteCoffee extends SimpleCoffee {
    public static String NAME = "拿铁咖啡";
    @Override
    public String getName() {
        return NAME;
    }
}
