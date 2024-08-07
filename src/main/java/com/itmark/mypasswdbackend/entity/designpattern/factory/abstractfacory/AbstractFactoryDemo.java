package com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory;

import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee.AbstractFactoryCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.dessert.AbstractFactoryDessert;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.factory.ItalyDessertFactory;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/7 14:44
 */
public class AbstractFactoryDemo {
    public static void main(String[] args) {
        // 特点：生产同一个产品族，需要添加一个产品族则新建一个产品类即可，需要生成一些列产品
        // 缺点：抽象工厂需要添加一个新的产品时候所有工厂都要修改
        ItalyDessertFactory factory = new ItalyDessertFactory();
        AbstractFactoryDessert dessert = factory.createDessert();
        AbstractFactoryCoffee coffee = factory.createCoffee();
        System.out.println(coffee.getName());
        dessert.show();
    }
}
