package com.hat.javaadvance.design_pattern.factory.factory_method;

/**
 * 手机工厂，所有品牌的手机工厂都要实现该工厂接口。
 */
public interface PhoneFactory {
    Phone create();
}
