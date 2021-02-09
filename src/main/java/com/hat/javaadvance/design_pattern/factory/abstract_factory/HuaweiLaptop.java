package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 具体产品（华为笔记本）
 */
public class HuaweiLaptop implements Computer {

    @Override
    public void name() {
        System.out.println("华为笔记本");
    }

    @Override
    public void produced() {
        System.out.println("华为笔记本产地：中国");
    }
}
