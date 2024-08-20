package com.itmark.mypasswdbackend.entity.designpattern.builder.demotwo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @description: 手机类，如果仅提供全参的构造方法，随着手机的零部件增多，则创建对象的难度也会越来越大，此处就可以使用到创建者模式
 * @author: MAKUAN
 * @date: 2024/8/20 14:14
 */
@Setter
@Getter
@ToString
public class Phone {

    /**
     * cpu
     */
    private String cpu;

    /**
     * 屏幕
     */
    private String screen;

    /**
     * 内存
     */
    private String memory;

    /**
     * 相机
     */
    private String camera;

    /**
     * 1 私有构建方法，传递一个构建器
     */
    private Phone(PhoneBuilder builder){
            this.cpu=builder.cpu;
            this.screen=builder.screen;
            this.memory=builder.memory;
            this.camera=builder.camera;
    }

    public static final class PhoneBuilder {
        /**
         * cpu
         */
        public String cpu;

        /**
         * 屏幕
         */
        public String screen;

        /**
         * 内存
         */
        public String memory;

        /**
         * 相机
         */
        public String camera;

        public PhoneBuilder cpu(String cpu){
            this.cpu =cpu;
            return this;
        }

        public PhoneBuilder screen(String screen){
            this.screen =screen;
            return this;
        }

        public PhoneBuilder memory(String memory){
            this.memory =memory;
            return this;
        }

        public PhoneBuilder camera(String camera){
            this.camera =camera;
            return this;
        }

        public Phone build(){
            return new Phone(this);
        }
    }
}
