package com.itmark.mypasswdbackend.entity.designpattern.factory.demo;

import java.util.Objects;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 15:30
 */
public class ConsumerTest {
    public static void main(String[] args) {
        CoffeeStore store = new CoffeeStore();
        Coffee coffee = store.orderCoffee(LatteCoffee.NAME);
        if (Objects.nonNull(coffee)){
            String name = coffee.getName();
            System.out.println(name);
        }
    }
}
