package com.itmark.mypasswdbackend.entity.designpattern.factory.configFactory;

/**
 * @description: 测试简单工厂模式
 * @author: MAKUAN
 * @date: 2024/8/5 15:45
 */
public class ConfigFactoryDemoTest {
    public static void main(String[] args) {
        ConfigCoffee coffee = ConfigCoffeeStaticFactory.createCoffee("americanCoffee");
        System.out.println(coffee.getName());
        coffee.addSugar();
        coffee.addMilk();
    }
}
