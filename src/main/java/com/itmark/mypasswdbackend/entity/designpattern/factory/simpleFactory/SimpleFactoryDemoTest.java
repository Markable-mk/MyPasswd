package com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory;

/**
 * @description: 测试简单工厂模式
 * @author: MAKUAN
 * @date: 2024/8/5 15:45
 */
public class SimpleFactoryDemoTest {
    public static void main(String[] args) {
        // 简单工厂
        SimpleCoffeeStore store = new SimpleCoffeeStore();
        SimpleCoffee simpleCoffee = store.orderCoffee(SimpleAmericanCoffee.NAME);
        System.out.println(simpleCoffee.getName());

        // 简单静态工厂
        /**
         * 优点：对象的创建和业务代码分离，静态工厂简化了工厂对象的创建
         * 缺点：业务代码和工厂类耦合在一起，违反开闭原则
         */
        SimpleCoffee coffee = SimpleCoffeeStaticFactory.createCoffee(SimpleLatteCoffee.NAME);
        coffee.addMilk();
        coffee.addSugar();

        System.out.println(coffee.getName());
    }
}
