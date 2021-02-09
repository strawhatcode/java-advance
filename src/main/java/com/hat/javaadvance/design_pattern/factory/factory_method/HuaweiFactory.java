package com.hat.javaadvance.design_pattern.factory.factory_method;

/**
 * 华为手机 的工厂，实现手机工厂接口，相当于 华为手机 专卖店。
 */
public class HuaweiFactory implements PhoneFactory{
    @Override
    public Phone create() {
        return new HuaweiPhone();
    }
}
