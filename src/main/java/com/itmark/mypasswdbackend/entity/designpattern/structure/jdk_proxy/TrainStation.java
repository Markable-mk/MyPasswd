package com.itmark.mypasswdbackend.entity.designpattern.structure.jdk_proxy;

/**
 * @description: 火车占
 * @author: MAKUAN
 * @date: 2024/10/12 13:42
 */
public class TrainStation implements SellTickets {

    @Override
    public void sellTicket() {
        System.out.println("火车站买票");
    }

}
