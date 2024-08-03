package com.itmark.mypasswdbackend.entity.designpattern.singleton;

import java.util.Objects;

/**
 * @description: 懒汉式-存在线程安全问题
 * @author: MAKUAN
 * @date: 2024/8/3 16:27
 */
public class MySingletonEntityLazyOne {
    // 1 私有构造方法-外界不让创建对象
    private MySingletonEntityLazyOne(){}
    // 2 声明该类型的变量，并没有创建对象，获取的时候进行创建对象
    private static MySingletonEntityLazyOne single ;
    // 3 提供一个公共的访问方式，让外界访问
    public static MySingletonEntityLazyOne getMySingletonEntity(){
        if (Objects.isNull(single)){
            single = new MySingletonEntityLazyOne();
        }
        return single;
    }
}
