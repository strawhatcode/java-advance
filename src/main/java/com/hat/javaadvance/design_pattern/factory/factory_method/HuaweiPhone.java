package com.hat.javaadvance.design_pattern.factory.factory_method;

/**
 * 该工厂可以创建HuaweiPhone华为手机，HuaweiPhone实现Phone接口
 */
public class HuaweiPhone implements Phone {

    @Override
    public void name() {
        System.out.println("华为手机");
    }

    @Override
    public void produced() {
        System.out.println("产地：中国");
    }
}
