package com.itmark.mypasswdbackend.entity.designpattern.singleton;

import java.util.Objects;

/**
 * @description: 懒汉式-synchronized解决线程安全问题
 * @author: MAKUAN
 * @date: 2024/8/3 16:27
 */
public class MySingletonEntityLazyTwo {
    // 1 私有构造方法-外界不让创建对象
    private MySingletonEntityLazyTwo(){}
    // 2 声明该类型的变量，并没有创建对象，获取的时候进行创建对象
    private static MySingletonEntityLazyTwo single ;
    // 3 提供一个公共的访问方式，让外界访问 synchronized
    public static synchronized MySingletonEntityLazyTwo getMySingletonEntity(){
        if (Objects.isNull(single)){
            // 只有一次写操作，后面都是读操作，如果每一个线程都加锁会导致线程问题
            single = new MySingletonEntityLazyTwo();
        }
        return single;
    }
}
