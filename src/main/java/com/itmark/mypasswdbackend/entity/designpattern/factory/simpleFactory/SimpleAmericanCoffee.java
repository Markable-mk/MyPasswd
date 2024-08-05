package com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:35
 */
public class SimpleAmericanCoffee extends SimpleCoffee {
    public static String NAME ="美式咖啡";
    @Override
    public String getName() {
        return NAME;
    }
}
