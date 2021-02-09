package com.hat.javaadvance.design_pattern.decorator;

/**
 * 装饰模式：不改变原对象的情况下给原对象新增功能或者特性，或者做一些其他的操作
 * 使用递归的方式来新增新特性，一个装饰器代表一个新特性
 */
public class Main {
    public static void main(String[] args) {
        Phone phone = new P40P();  // 一台P40P裸机
        phone = new ChargerDecorator(phone); // 给手机添加充电器
        phone= new InstructionsDecorator(phone); // 添加说明书
        phone= new EarphoneDecorator(phone);  // 添加耳机
        phone.wrapper(); //添加完新特性后最终的结果
    }
}
