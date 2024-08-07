package com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.factoryclass;

import com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.MethodCoffee;

/**
 * @description: 抽象工厂
 * @author: MAKUAN
 * @date: 2024/8/7 10:25
 */
public interface MethodCoffeeFactory {
    /**
     * 创建coffee方法
     * @return
     */
    MethodCoffee createCoffee();
}
