package com.itmark.mypasswdbackend.entity.designpattern.factory.configFactory;


/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:37
 */
public class ConfigCoffeeStore {

    public ConfigCoffee orderCoffee(String beanName){
        return ConfigCoffeeStaticFactory.createCoffee(beanName);
    }

}
