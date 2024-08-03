package com.itmark.mypasswdbackend.entity.designpattern.singleton;

import java.io.Serializable;

/**
 * @description: 饿汉式-静态成员变量
 * @author: MAKUAN
 * @date: 2024/8/3 16:27
 */
public class MySingletonEntityHungryOne implements Serializable {
    // 1 私有构造方法-外界不让创建对象
    private MySingletonEntityHungryOne(){}
    // 2 在本类中创建本类对象
    private static MySingletonEntityHungryOne single = new MySingletonEntityHungryOne();
    // 3 提供一个公共的访问方式，让外界访问
    public static MySingletonEntityHungryOne getMySingletonEntity(){
        return MySingletonEntityHungryOne.single;
    }

    // 4 档进行反序列化时候，会自动调用该方法返回对象
    public Object readResolve(){
        return MySingletonEntityHungryOne.single;
    }
}
