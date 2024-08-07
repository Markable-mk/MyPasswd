package com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod;

import com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.factoryclass.MethodAmericanCoffeeFactory;

/**
 * @description: 工厂方法测试类-对修改关闭，对扩展开放，满足了开闭原则，缺点开放造成了类可能过多增加了系统复杂度
 * @author: MAKUAN
 * @date: 2024/8/7 10:36
 */
public class MethodConsumerTest {
    public static void main(String[] args) {
        // 咖啡店
        MethodCoffeeStore store = new MethodCoffeeStore();
        // 工厂
        MethodAmericanCoffeeFactory methodAmericanCoffeeFactory = new MethodAmericanCoffeeFactory();
        // 设置工厂
        store.setFactory(methodAmericanCoffeeFactory);
        // 点咖啡
        MethodCoffee methodCoffee = store.orderCoffee();
        System.out.println(methodCoffee.getName());
    }
}
