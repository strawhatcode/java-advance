package com.hat.javaadvance.design_pattern.decorator;

/**
 * 具体装饰器（ConcreteDecorator），继承凑想装饰器，这个是充电器装饰器
 * 实现抽象装饰器的方法（如有）
 * 新增Phone组件的特性方法，重写Phone接口的wrapper()方法，把新特性方法加进去
 */
public class ChargerDecorator extends PhoneDecorator{
    public ChargerDecorator(Phone phone) {
        super(phone);
    }

    @Override
    public void wrapper() {
        super.wrapper();
        this.add(); // 把新特性添加到Phone接口的wrapper()方法
    }

    @Override
    protected void add() {
        System.out.println("附带充电器");
    }
}
