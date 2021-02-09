package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 具体工厂（联想电子产品工厂），继承电子产品（ElectronicFactory）类
 */
public class LenovoElectronicFactory  extends ElectronicFactory{
    @Override
    public Phone createPhone() {
        return new LenovoPhone();
    }

    @Override
    public Computer createComputer() {
        return new LenovoComputer();
    }
}
