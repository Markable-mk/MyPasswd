package com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod;

import com.itmark.mypasswdbackend.entity.designpattern.factory.factorymethod.factoryclass.MethodCoffeeFactory;
import com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory.SimpleCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory.SimpleCoffeeFactory;

import java.util.Objects;

/**
 * @description: 上架一个新的产品，就要修改代码 new 新的Coffee 违背了开闭原则
 * @author: MAKUAN
 * @date: 2024/8/5 11:37
 */
public class MethodCoffeeStore {

    private MethodCoffeeFactory factory;

    public void setFactory(MethodCoffeeFactory factory){
        this.factory=factory;
    }

    public MethodCoffee orderCoffee(){
        MethodCoffee coffee = factory.createCoffee();
        coffee.addMilk();
        coffee.addSugar();
        return coffee;
    }

}
