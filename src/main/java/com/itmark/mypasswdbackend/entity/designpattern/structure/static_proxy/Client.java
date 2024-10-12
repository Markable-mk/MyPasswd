package com.itmark.mypasswdbackend.entity.designpattern.structure.static_proxy;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/10/12 13:44
 */
public class Client {
    public static void main(String[] args) {
        // 客户端没有直接访问火车站，使用了代理类，间接访问目标类，并且代理类在访问目标类前做了增强，也不影响原始目标类的功能升级
        ProxyPoint proxyPoint = new ProxyPoint();
        proxyPoint.sellTicket();
    }
}
