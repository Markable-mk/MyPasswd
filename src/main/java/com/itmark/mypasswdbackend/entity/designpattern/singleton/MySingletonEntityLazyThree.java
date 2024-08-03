package com.itmark.mypasswdbackend.entity.designpattern.singleton;

import java.util.Objects;

/**
 * 双重检查锁看似解决了单例、性能、线程安全问题，但是在多线程情况下，可能会出现空指针问题，原因是JVM在实例化对象时候会进行优化和指令重排序操作
 * 要解决指令重排序问题只需要加上volatile关键字，volatile解决了可见性和指令重排序的问题
 * @description: 懒汉式-双重检查锁
 * @author: MAKUAN
 * @date: 2024/8/3 16:27
 */
public class MySingletonEntityLazyThree {
    // 1 私有构造方法-外界不让创建对象
    private MySingletonEntityLazyThree(){}
    // 2 声明该类型的变量，并没有创建对象，获取的时候进行创建对象
    private static volatile MySingletonEntityLazyThree single ;
    // 3 提供一个公共的访问方式，让外界访问
    public static MySingletonEntityLazyThree getMySingletonEntity(){
        if (Objects.isNull(single)){
            synchronized (MySingletonEntityLazyThree.class){
                if (Objects.isNull(single)){
                    single = new MySingletonEntityLazyThree();
                }
            }
        }
        return single;
    }
}
