package com.itmark.mypasswdbackend.service.demo.forde;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/11/12 12:16
 */
public class ForDemo {
    void forDemo(){
        co:for (int i = 0; i < 20; i++) {
            bo:for (int i1 = 0; i1 < 10; i1++) {
                System.out.println(i1);
                if (i1>5){
                    // 可以通过标号来break循环
                    break co;
                }
            }
        }
    }
}
