package com.itmark.mypasswdbackend.entity.designpattern.builder.demoone;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/19 9:35
 */
public abstract class BikeBuilder {

    /**
     * 声明Bike类型变量，并进行赋值
     */
    protected Bike bike = new Bike();

    /**
     * 构建车架
     */
    public abstract void buildFrame();

    /**
     * 构建车座
     */
    public abstract void buildSeat();

    /**
     * 构建自行车
     * @return
     */
    public abstract Bike createBike();
}
