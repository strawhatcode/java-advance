package com.hat.javaadvance.design_pattern.bridge;

/**
 * 具体实现化角色，实现接口的方法
 */
public class HuaweiP40P implements PhoneModel{
    @Override
    public void phoneModel(PhoneParameter parameter) {
        System.out.println("华为P40P");
        System.out.println(parameter);
    }
}
