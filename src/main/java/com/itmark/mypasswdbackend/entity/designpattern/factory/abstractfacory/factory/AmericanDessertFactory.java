package com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.factory;

import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee.AbstractFactoryCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.dessert.AbstractFactoryDessert;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee.AbstractFactoryAmericanCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.dessert.MatchaMousse;

/**
 * @description: 美式风味甜品工厂：生产美式咖啡和抹茶慕斯
 * @author: MAKUAN
 * @date: 2024/8/7 14:40
 */
public class AmericanDessertFactory implements AbstractDessertFactory{
    @Override
    public AbstractFactoryCoffee createCoffee() {
        return new AbstractFactoryAmericanCoffee();
    }

    @Override
    public AbstractFactoryDessert createDessert() {
        return new MatchaMousse();
    }
}
