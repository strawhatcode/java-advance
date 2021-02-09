package com.hat.javaadvance.design_pattern.decorator;

/**
 * 抽象装饰器（Decorator）：实现抽象组件（Component）
 * 并且有个抽象组件（Component）的属性
 */
public abstract class PhoneDecorator implements Phone{
    private Phone phone;

    public PhoneDecorator(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void wrapper() {
        this.phone.wrapper();
    }

    // 新增一个抽象方法，如果子类非必须有该方法可以不加，根据具体业务需求而定
    protected abstract void add();
}
