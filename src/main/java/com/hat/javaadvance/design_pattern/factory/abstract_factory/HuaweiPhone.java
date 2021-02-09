package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 具体产品（华为手机）
 */
public class HuaweiPhone implements Phone{
    @Override
    public void name() {
        System.out.println("华为手机");
    }

    @Override
    public void produced() {
        System.out.println("华为手机产地：中国");
    }
}
