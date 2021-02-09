package com.hat.javaadvance.design_pattern.factory.simple_factory;

/**
 * 该工厂可以创建SonyPhone索尼手机，SonyPhone实现Phone接口
 */
public class SonyPhone implements Phone {
    @Override
    public void name() {
        System.out.println("索尼手机");
    }

    @Override
    public void produced() {
        System.out.println("产地：日本");
    }
}
