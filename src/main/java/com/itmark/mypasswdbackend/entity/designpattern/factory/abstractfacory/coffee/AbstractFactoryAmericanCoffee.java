package com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:35
 */
public class AbstractFactoryAmericanCoffee extends AbstractFactoryCoffee {
    public static String NAME ="美式咖啡";
    @Override
    public String getName() {
        return NAME;
    }
}
