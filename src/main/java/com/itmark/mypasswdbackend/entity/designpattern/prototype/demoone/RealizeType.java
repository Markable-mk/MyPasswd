package com.itmark.mypasswdbackend.entity.designpattern.prototype.demoone;

/**
 * java中Object提供了clone方法实现浅克隆（内存地址不同），Cloneable接口为抽象原型类，而实现了Cloneable接口的子类就是具体的原型类
 * @description:  原型模式使用场景：1对象创建比较复杂，2性能和安全要求比较高
 * @author: MAKUAN
 * @date: 2024/8/15 14:18
 */
public class RealizeType implements Cloneable{

    /**
     * 发现调用clone方法后，克隆出来的对象并不是通过构造方法创建的
     */
    public RealizeType(){
        System.out.println("--具体的原型对象创建成功--");
    }

    @Override
    protected RealizeType clone() throws CloneNotSupportedException {
        System.out.println("--复制成功--");
        return (RealizeType)super.clone();
    }
}
