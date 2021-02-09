package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 具体工厂（华为电子才产品工厂），继承电子产品工厂（ElectronicFactory）
 */
public class HuaweiElectronicFactory extends ElectronicFactory {
    @Override
    public Phone createPhone() {
        return new HuaweiPhone();
    }

    @Override
    public Computer createComputer() {
        return new HuaweiLaptop();
    }
}
