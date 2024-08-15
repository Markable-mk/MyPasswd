package com.itmark.mypasswdbackend.entity.designpattern.prototype.demothree;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 浅克隆：创建一个新对象,新对象的属性和原来对象完全相同，对于非基本属性类型，仍然执行属性所执行的内存地址
 * 深克隆：创建一个新对象，属性中引用的其他对象也会被克隆，不在指向原有对象地址（使用对象操作流）
 * @description:
 * @author: MAKUAN
 * @date: 2024/8/15 15:21
 */
public class Demo3 {
    public static void main(String[] args) throws Exception {
        // 浅克隆
        Certificate certificate = new Certificate();
        Student student = new Student();
        student.setName("张胜");
        certificate.setStudent(student);

        Certificate clone = certificate.clone();
        clone.getStudent().setName("李宗");
        certificate.show();
        clone.show();
        // 深克隆
        // 1 序列化对象到文件
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("D:\\applicaions\\IdeaProjects\\myCompanyProjs\\MyPasswdBackend\\src\\main\\java\\com\\itmark\\mypasswdbackend\\entity\\designpattern\\prototype\\demothree\\certi.txt"));
        objectOutputStream.writeObject(certificate);
        objectOutputStream.close();
        // 2 反序列化对象
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("D:\\applicaions\\IdeaProjects\\myCompanyProjs\\MyPasswdBackend\\src\\main\\java\\com\\itmark\\mypasswdbackend\\entity\\designpattern\\prototype\\demothree\\certi.txt"));
        Certificate certificateDeep = (Certificate) objectInputStream.readObject();
        objectInputStream.close();
        Student student1 = certificateDeep.getStudent();
        student1.setName("李磊");
        certificateDeep.show();
    }
}
