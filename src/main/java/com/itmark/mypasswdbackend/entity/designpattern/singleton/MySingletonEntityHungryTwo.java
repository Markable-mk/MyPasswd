package com.itmark.mypasswdbackend.entity.designpattern.singleton;

/**
 * @description: 饿汉-静态代码块方式
 * @author: MAKUAN
 * @date: 2024/8/3 16:32
 */
public class MySingletonEntityHungryTwo {
    // 1 私有构造方法-外界不让创建对象
    private MySingletonEntityHungryTwo(){}
    // 2 声明私有变量
    private static MySingletonEntityHungryTwo single;
    // 3 静态代码块中赋值
    static {
        single = new MySingletonEntityHungryTwo();
    }

    // 3 公共访问方法
    public static MySingletonEntityHungryTwo getMySingletonEntity(){
        return single;
    }
}
