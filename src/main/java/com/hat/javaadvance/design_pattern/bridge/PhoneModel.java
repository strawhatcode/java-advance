package com.hat.javaadvance.design_pattern.bridge;

/**
 * 实现化角色接口（手机型号），手机型号的接口方法接受另一个具体实现化角色（手机参数）
 */
public interface PhoneModel {
    void phoneModel(PhoneParameter parameter);
}
