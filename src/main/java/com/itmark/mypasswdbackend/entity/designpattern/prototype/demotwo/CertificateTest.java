package com.itmark.mypasswdbackend.entity.designpattern.prototype.demotwo;

import com.itmark.mypasswdbackend.entity.designpattern.prototype.demotwo.Certificate;

/**
 * java中Object提供了clone方法实现浅克隆（内存地址不同），Cloneable接口为抽象原型类，而实现了Cloneable接口的子类就是具体的原型类
 * @description: 原型模式使用场景：1对象创建比较复杂，2性能和安全要求比较高
 * @author: MAKUAN
 * @date: 2024/8/15 14:31
 */
public class CertificateTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        Certificate certificateOne = new Certificate();
        Certificate cloneCertificateTwo = certificateOne.clone();
        certificateOne.setName("张胜男");
        cloneCertificateTwo.setName("李磊");
        certificateOne.show();
        cloneCertificateTwo.show();
    }
}
