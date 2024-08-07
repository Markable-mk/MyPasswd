package com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.factoryclass;

import com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.MethodAmericanCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.MethodCoffee;

/**
 * @description: 美式咖啡工厂专门生成拿铁咖啡
 * @author: MAKUAN
 * @date: 2024/8/7 10:30
 */
public class MethodLatteCoffeeFactory implements MethodCoffeeFactory{
    @Override
    public MethodCoffee createCoffee() {

        return new MethodAmericanCoffee();
    }
}
