package com.itmark.mypasswdbackend.entity.designpattern.factory.simpleFactory;



import java.util.Objects;

/**
 * @description: 简单工厂
 * @author: MAKUAN
 * @date: 2024/8/5 11:37
 */
public class SimpleCoffeeStaticFactory {

    public static SimpleCoffee createCoffee(String type){
        SimpleCoffee result = null;
        // 根据类型创建不同的Coffee子类对象
        if (SimpleAmericanCoffee.NAME.equals(type)){
            result = new SimpleAmericanCoffee();
        }
        if (SimpleLatteCoffee.NAME.equals(type)) {
            result = new SimpleLatteCoffee();
        }
        if (Objects.isNull(result)){
           throw new RuntimeException("你点的咖啡暂未上架");
        }
        return result;
    }

}
