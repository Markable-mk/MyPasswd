package com.itmark.mypasswdbackend.entity.designpattern.prototype.demoone;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/15 14:23
 */
public class RealizeTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        RealizeType realizeType = new RealizeType();
        RealizeType clone = realizeType.clone();
        System.out.println(realizeType);
        System.out.println(clone);
    }
}
