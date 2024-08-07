package com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.factory;

import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.coffee.AbstractFactoryCoffee;
import com.itmark.mypasswdbackend.entity.designpattern.factory.abstractfacory.dessert.AbstractFactoryDessert;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/7 14:37
 */
public interface AbstractDessertFactory {

    /**
     * 创建咖啡
     * @return
     */
    AbstractFactoryCoffee createCoffee();

    /**
     * 创建甜品
     * @return
     */
    AbstractFactoryDessert createDessert();

}
