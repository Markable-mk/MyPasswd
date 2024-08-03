package com.itmark.mypasswdbackend.entity.designpattern.singleton;

/**
 * 静态内部类单例模式，，实例对象由内部类创建，由于JVM在加载外部类的过程中，是不会加载静态内部类的，
 * 只有内部类的属性、方法被调用时候才会被加载，并初始化其静态属性，
 * 静态属性由于被static修饰，保证只被实例化一次，并且严格保证实例化顺序
 *
 * @description: 懒汉式-静态内部类方式
 * @author: MAKUAN
 * @date: 2024/8/3 16:27
 */
public class MySingletonEntityLazyFour {

    // 处理反射造对单例的破坏
    private static boolean flag = false;

    // 1 私有构造方法-外界不让创建对象
    private MySingletonEntityLazyFour() {
        if (flag) {
            // 第二次调用这个无参构造报错
            throw new RuntimeException("不能创建多个对象！");
        }
        flag = true;
    }

    // 2 定义静态内部类
    private static class MyLazyEntityHungryFourHolder {
        // final 防止外界修改，final修饰的通常为常量 应该大写
        private static final MySingletonEntityLazyFour SINGLE = new MySingletonEntityLazyFour();
    }

    // 3 返回内部类中的单例对象
    public static MySingletonEntityLazyFour getMyLazyEntityHungryFour() {
        return MyLazyEntityHungryFourHolder.SINGLE;
    }
}
