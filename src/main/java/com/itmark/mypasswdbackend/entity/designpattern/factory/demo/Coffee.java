package com.itmark.mypasswdbackend.entity.designpattern.factory.demo;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/5 11:33
 */
public abstract class Coffee {

    public abstract String getName();

    public void addSugar(){
        System.out.println("加糖");
    }

    public void addMilk(){
        System.out.println("加奶");
    }
}
