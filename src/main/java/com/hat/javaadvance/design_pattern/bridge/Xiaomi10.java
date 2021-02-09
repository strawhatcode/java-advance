package com.hat.javaadvance.design_pattern.bridge;

/**
 * 具体实现化角色，实现接口的方法
 */
public class Xiaomi10 implements PhoneModel {
    @Override
    public void phoneModel(PhoneParameter parameter) {
        System.out.println("小米10");
        System.out.println(parameter);
    }
}
