package com.hat.javaadvance.design_pattern.builder.classic;

/**
 * 经典构造器模式
 *
 */
public class Main {
    public static void main(String[] args) {
        Director director = new Director(new HuaweiPhoneBuilder());
        Phone phone = director.buildPhone();
        System.out.println(phone);
    }
}
