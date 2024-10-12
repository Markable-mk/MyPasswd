package com.itmark.mypasswdbackend.entity.designpattern.structure.jdk_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @description: 获取代理对象的工厂类，代理类也实现了对应的接口
 * @author: MAKUAN
 * @date: 2024/10/12 13:42
 */
public class ProxyFactory {

    /**
     * 声明目标对象
     */
    private TrainStation trainStation = new TrainStation();

    /**
     * 返回代理对象
     *
     * @return
     */
    public SellTickets getProxyObject() {
        /**
         * 代理对象是该对象的子类
         * ClassLoader loader,类加载器：用于加载代理类，可以通过目标对象获取类加载器
         * Class<?>[] interfaces,代理类实现接口的字节码对象
         * InvocationHandler h,代理对象调用的处理程序
         */
        SellTickets obj = (SellTickets) Proxy.newProxyInstance(trainStation.getClass().getClassLoader(), trainStation.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("代售点收取");
                /**
                 * Object proxy,代理对象和SellTickets obj一样
                 * Method method,对接口中的方法进行封装的的对象，卖票方法
                 * Object[] args,调用方法的实际参数
                 * 返回值：方法的返回值，没有返回值null，否则返回具体的值
                 */
                // 执行目标对象的方法（method对接口方法进行了封装，所以可以这样使用调用了其他实现接口类对象的方法）
                Object result = method.invoke(trainStation, args);
                return result;
            }
        });
        return obj;
    }

}
