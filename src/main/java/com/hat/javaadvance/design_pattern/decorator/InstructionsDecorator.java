package com.hat.javaadvance.design_pattern.decorator;


/**
 * 具体装饰器（InstructionsDecorator），继承抽象装饰器，这个是说明书装饰器
 * 实现抽象装饰器的方法（如有）
 * 新增Phone组件的特性方法，重写Phone接口的wrapper()方法，把新特性方法加进去
 */
public class InstructionsDecorator extends PhoneDecorator {

    public InstructionsDecorator(Phone phone) {
        super(phone);
    }

    @Override
    public void wrapper() {
        super.wrapper();
        this.add();
    }

    @Override
    protected void add() {
        System.out.println("附带说明书");
    }
}
