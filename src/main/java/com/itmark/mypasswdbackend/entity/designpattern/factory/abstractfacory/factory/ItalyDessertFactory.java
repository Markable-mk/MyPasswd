package com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.factory;

import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee.AbstractFactoryCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.dessert.AbstractFactoryDessert;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee.AbstractFactoryLatteCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.dessert.Trimisu;

/**
 * @description:意大利风味甜品工厂：生产拿铁和慕斯
 * @author: MAKUAN
 * @date: 2024/8/7 14:42
 */
public class ItalyDessertFactory implements AbstractDessertFactory{
    @Override
    public AbstractFactoryCoffee createCoffee() {
        return new AbstractFactoryLatteCoffee();
    }

    @Override
    public AbstractFactoryDessert createDessert() {
        return new Trimisu();
    }
}
