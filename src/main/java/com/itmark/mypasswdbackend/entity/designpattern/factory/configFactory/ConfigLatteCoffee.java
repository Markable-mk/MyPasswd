package com.itmark.mypasswdbackend.entity.designpattern.factory.configFactory;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:35
 */
public class ConfigLatteCoffee extends ConfigCoffee {

    public static String NAME = "拿铁咖啡";
    @Override
    public String getName() {
        return NAME;
    }

}
