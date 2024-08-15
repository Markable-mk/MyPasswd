package com.itmark.mypasswdbackend.entity.designpattern.prototype.demotwo;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/15 14:28
 */
public class Certificate implements Cloneable{

    private String name;

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    @Override
    public Certificate clone() throws CloneNotSupportedException {
        return (Certificate)super.clone();
    }

    public void show(){
        System.out.println(this.name+"的证书生成了！");
    }
}
