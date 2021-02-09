package com.hat.javaadvance.design_pattern.bridge;

/**
 * 扩展抽象化角色（买手机），实现抽象方法
 */
public class BuyPhone extends Phone {

    public BuyPhone(PhoneModel model, PhoneParameter parameter) {
        super(model, parameter);
    }

    @Override
    public void getPhone() {
        model.phoneModel(parameter);
    }
}
