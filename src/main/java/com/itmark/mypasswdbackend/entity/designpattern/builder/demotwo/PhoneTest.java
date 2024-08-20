package com.itmark.mypasswdbackend.entity.designpattern.builder.demotwo;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/20 14:27
 */
public class PhoneTest {
    public static void main(String[] args) {
        // 1 创建手机对象
        Phone build = new Phone.PhoneBuilder()
                .cpu("高通")
                .screen("三星")
                .memory("金士顿")
                .camera("徕卡")
                .build();
        System.out.println(build);
    }
}
