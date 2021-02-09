package com.hat.javaadvance.design_pattern.factory.factory_method;

/**
 * 索尼手机的工厂，实现手机工厂接口，相当于索尼手机专卖店。
 */
public class SonyFactory implements PhoneFactory {
    @Override
    public Phone create() {
        return new SonyPhone();
    }
}
