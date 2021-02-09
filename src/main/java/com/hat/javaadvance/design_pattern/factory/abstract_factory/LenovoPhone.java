package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 具体产品（联想手机）
 */
public class LenovoPhone implements Phone {
    @Override
    public void name() {
        System.out.println("联想手机");
    }

    @Override
    public void produced() {
        System.out.println("联想手机产地：美国");
    }
}
