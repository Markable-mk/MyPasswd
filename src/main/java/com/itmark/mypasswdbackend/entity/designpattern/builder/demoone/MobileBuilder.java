package com.itmark.mypasswdbackend.entity.designpattern.builder.demoone;

/**
 * @description: 具体构建者用于构建摩拜单车对象
 * @author: MAKUAN
 * @date: 2024/8/19 9:38
 */
public class MobileBuilder extends BikeBuilder{

    @Override
    public void buildFrame() {
        super.bike.setFrame("碳纤维车架");
    }

    @Override
    public void buildSeat() {
        super.bike.setSeat("皮座");
    }

    @Override
    public Bike createBike() {
        return super.bike;
    }
}
