package com.hat.javaadvance.design_pattern.decorator;

/**
 * 具体装饰器（EarphoneDecorator），继承凑想装饰器，这个是耳机装饰器
 * 实现抽象装饰器的方法（如有）
 * 新增Phone组件的特性方法，重写Phone接口的wrapper()方法，把新特性方法加进去
 */
public class EarphoneDecorator extends PhoneDecorator {

    public EarphoneDecorator(Phone phone) {
        super(phone);
    }

    @Override
    public void wrapper() {
        super.wrapper();
        this.add();
    }

    @Override
    protected void add() {
        System.out.println("附带耳机");
    }
}
