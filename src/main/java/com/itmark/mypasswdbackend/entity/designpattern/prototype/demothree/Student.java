package com.itmark.mypasswdbackend.entity.designpattern.prototype.demothree;

import java.io.Serializable;

/**
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/15 15:19
 */
public class Student implements Serializable {

    private String name;
    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                '}';
    }
}
