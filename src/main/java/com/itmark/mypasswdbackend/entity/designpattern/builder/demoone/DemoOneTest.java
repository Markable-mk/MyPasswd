package com.itmark.mypasswdbackend.entity.designpattern.builder.demoone;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/19 9:44
 */
public class DemoOneTest {
    public static void main(String[] args) {
        MobileBuilder mobileBuilder = new MobileBuilder();
        QingJuBuilder qingJuBuilder = new QingJuBuilder();
        Director directorMobile = new Director(mobileBuilder);
        Director directorQingJu = new Director(qingJuBuilder);
        Bike mobileBike = directorMobile.constructBike();
        Bike qingJuBike = directorQingJu.constructBike();
        System.out.println(mobileBike.getFrame());
        System.out.println(qingJuBike.getFrame());
    }

}
