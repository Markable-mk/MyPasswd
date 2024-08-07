package com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:35
 */
public class MethodLatteCoffee extends MethodCoffee {
    public static String NAME = "拿铁咖啡";
    @Override
    public String getName() {
        return NAME;
    }
}
