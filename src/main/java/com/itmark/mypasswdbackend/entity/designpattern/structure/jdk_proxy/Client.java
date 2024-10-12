package com.itmark.mypasswdbackend.entity.designpattern.structure.jdk_proxy;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/10/12 13:44
 */
public class Client {
    public static void main(String[] args) {
        // 创建工厂
        ProxyFactory proxyFactory = new ProxyFactory();
        // 获取代理对象
        SellTickets proxyObject = proxyFactory.getProxyObject();
        // 卖票
        proxyObject.sellTicket();
        // class com.sun.proxy.$Proxy0
        System.out.println(proxyObject.getClass());
       // while (true){}
    }
}
