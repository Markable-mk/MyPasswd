package com.itmark.mypasswdbackend.service.designpattern;

import com.itmark.mypasswdbackend.entity.designpattern.singleton.*;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.lang.reflect.Constructor;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/3 16:24
 */
@Slf4j
public class DesignPatternServiceImpl implements DesignPatternService{

    @Override
    public void singleton() {
        // 1.1 饿汉式-静态成员变量方式-获取单例对象
        MySingletonEntityHungryOne mySingletonEntity1 = MySingletonEntityHungryOne.getMySingletonEntity();
        MySingletonEntityHungryOne mySingletonEntity2 = MySingletonEntityHungryOne.getMySingletonEntity();
        // 1.2判断两个对象是否一样
        log.info("判断是否一样：{}",mySingletonEntity1==mySingletonEntity2);
        // 2.1 饿汉式-静态代码块方式-获取单例对象
        MySingletonEntityHungryTwo mySingletonEntity3 = MySingletonEntityHungryTwo.getMySingletonEntity();
        MySingletonEntityHungryTwo mySingletonEntity4 = MySingletonEntityHungryTwo.getMySingletonEntity();
        // 2.2 饿汉式-静态代码块方式-获取单例对象
        log.info("判断是否一样：{}",mySingletonEntity3==mySingletonEntity4);
        // 3.1 懒汉式-线程不安全
        MySingletonEntityLazyOne mySingletonEntity5 = MySingletonEntityLazyOne.getMySingletonEntity();
        MySingletonEntityLazyOne mySingletonEntity6 = MySingletonEntityLazyOne.getMySingletonEntity();
        log.info("判断是否一样：{}",mySingletonEntity3==mySingletonEntity4);
        MySingletonEntityLazyTwo mySingletonEntity7 = MySingletonEntityLazyTwo.getMySingletonEntity();
        MySingletonEntityLazyThree mySingletonEntity8 = MySingletonEntityLazyThree.getMySingletonEntity();
        MySingletonEntityLazyFour myLazyEntityHungryFour9 = MySingletonEntityLazyFour.getMyLazyEntityHungryFour();
    }

    @SneakyThrows
    @Override
    public void destorySingleton() {
        // 1 序列化反序列化方式破坏单例模式--需要在对应类中添加readResolve方法如果没有定义则返回新new的对象
        MySingletonEntityHungryOne mySingletonEntity1 = MySingletonEntityHungryOne.getMySingletonEntity();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("路径/文件.txt"));
        objectOutputStream.writeObject(mySingletonEntity1);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("路径/文件.txt"));
        MySingletonEntityHungryOne o = (MySingletonEntityHungryOne)objectInputStream.readObject();
        objectInputStream.close();
        log.info("判断是否一样：{}",mySingletonEntity1==o);

        // 2 反射破坏单例模式
        // 2.1 获取字节码对象
        Class<MySingletonEntityHungryOne> mySingletonEntityHungryOneClass = MySingletonEntityHungryOne.class;
        // 2.2获取无参构造方法对象
        Constructor<MySingletonEntityHungryOne> declaredConstructor = mySingletonEntityHungryOneClass.getDeclaredConstructor();
        // 2.3 取消访问检查 因为是私有的
        declaredConstructor.setAccessible(true);
        MySingletonEntityHungryOne mySingletonEntityHungryOne1 = declaredConstructor.newInstance();
        MySingletonEntityHungryOne mySingletonEntityHungryOne2 = declaredConstructor.newInstance();
        log.info("判断是否一样：{}",mySingletonEntityHungryOne1==mySingletonEntityHungryOne2);


    }

    @SneakyThrows
    @Override
    public void singletonOfJava() {
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("ipconfig");
        InputStream inputStream = process.getInputStream();
        byte[] bytes = new byte[1024 * 1024 * 100];
        // 返回读取到了 多少个字节
        int len = inputStream.read(bytes);
        log.info(new String(bytes,0,len,"GBK"));
    }

}
