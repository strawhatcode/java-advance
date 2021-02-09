package com.hat.javaadvance.design_pattern.bridge;

/**
 * 抽象化角色（手机）。该角色有两个实现化角色（手机型号和参数）
 * 提供一个抽象方法，获取该具体角色的信息
 */
public abstract class Phone {
    protected PhoneModel model;
    protected PhoneParameter parameter;

    public Phone(PhoneModel model, PhoneParameter parameter) {
        this.model = model;
        this.parameter = parameter;
    }

    public abstract void getPhone();
}
