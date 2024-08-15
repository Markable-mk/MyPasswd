package com.itmark.mypasswdbackend.entity.designpattern.prototype.demothree;

import java.io.Serializable;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/15 14:28
 */
public class Certificate implements Cloneable , Serializable {

    private Student stu;

    public Student getStudent(){
        return this.stu;
    }

    public void setStudent(Student stu){
        this.stu = stu;
    }

    @Override
    public Certificate clone() throws CloneNotSupportedException {
        return (Certificate)super.clone();
    }

    public void show(){
        System.out.println(this.stu.getName()+"的证书生成了！");
    }
}
