package com.itmark.mypasswdbackend.entity.designpattern.factory.demo;

import java.util.Objects;

/**
 * @description: 上架一个新的产品，就要修改代码 new 新的Coffee 违背了开闭原则
 * @author: MAKUAN
 * @date: 2024/8/5 11:37
 */
public class CoffeeStore {

    public Coffee orderCoffee(String type){
        Coffee result = null;
        // 根据类型创建不同的Coffee子类对象
        if (AmericanCoffee.NAME.equals(type)){
            result = new AmericanCoffee();
        }
        if (LatteCoffee.NAME.equals(type)) {
            result = new LatteCoffee();
        }
        if (Objects.isNull(result)){
           throw new RuntimeException("你点的咖啡暂未上架");
        }
        if (Objects.nonNull(result)){
            result.addMilk();
            result.addSugar();
        }
        return result;
    }

}
