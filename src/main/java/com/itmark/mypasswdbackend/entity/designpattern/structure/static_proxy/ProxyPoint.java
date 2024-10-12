package com.itmark.mypasswdbackend.entity.designpattern.structure.static_proxy;

/**
 * @description: 代理类
 * @author: MAKUAN
 * @date: 2024/10/12 13:42
 */
public class ProxyPoint implements SellTickets {

    private TrainStation trainStation = new TrainStation();

    @Override
    public void sellTicket() {
        System.out.println("服务增强-代理收费");
        trainStation.sellTicket();
    }

}
