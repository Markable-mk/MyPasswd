package com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:35
 */
public class AbstractFactoryLatteCoffee extends AbstractFactoryCoffee {
    public static String NAME = "拿铁咖啡";
    @Override
    public String getName() {
        return NAME;
    }
}
