package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 具体产品（联想电脑）
 */
public class LenovoComputer implements Computer{
    @Override
    public void name() {
        System.out.println("联想台式机");
    }

    @Override
    public void produced() {
        System.out.println("联想台式机产地：美国");
    }
}
