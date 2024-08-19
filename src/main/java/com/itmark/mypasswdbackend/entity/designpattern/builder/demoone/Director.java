package com.itmark.mypasswdbackend.entity.designpattern.builder.demoone;

/**
 * @description: 指挥者类，指导构建者如何构建产品，单一职责原则
 * @author: MAKUAN
 * @date: 2024/8/19 9:41
 */
public class Director {

    /**
     * 声明builder类型变量，在创建Director对象时候赋值
     */
    private BikeBuilder builder;

    /**
     * 提供有参构造方法
     * @param builder
     */
    public Director(BikeBuilder builder) {
        this.builder = builder;
    }

    /**
     * 不需要指挥者了就，加重了抽象指挥者职测
     * @return
     */
    public Bike constructBike() {
        builder.buildFrame();
        builder.buildSeat();
        return builder.createBike();
    }
}
