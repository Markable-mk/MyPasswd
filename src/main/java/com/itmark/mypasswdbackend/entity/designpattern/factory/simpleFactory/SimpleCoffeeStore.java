package com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory;

import com.itmark.mypasswdbackend.entity.designpattern.factory.demo.AmericanCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.demo.Coffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.demo.LatteCoffee;

import java.util.Objects;

/**
 * @description: 上架一个新的产品，就要修改代码 new 新的Coffee 违背了开闭原则
 * @author: MAKUAN
 * @date: 2024/8/5 11:37
 */
public class SimpleCoffeeStore {

    public SimpleCoffee orderCoffee(String type){
        SimpleCoffeeFactory factory =   new SimpleCoffeeFactory();
        SimpleCoffee coffee = factory.createCoffee(type);

        if (Objects.isNull(coffee)){
           throw new RuntimeException("你点的咖啡暂未上架");
        }

        coffee.addMilk();
        coffee.addSugar();

        return coffee;
    }

}
