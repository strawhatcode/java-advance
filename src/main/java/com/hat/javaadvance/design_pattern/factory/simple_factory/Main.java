package com.hat.javaadvance.design_pattern.factory.simple_factory;

/**
 * 简单工厂模式；对一种产品进行封装
 *      一个抽象产品（Phone）、
 *      多个具体产品（SonyPhone、HuaweiPhone...）、
 *      一个具体工厂（PhoneFactory）
 * 使用Factory对 一种产品 进行封装，每次创建对象时只需要调用工厂的create方法创建
 * 缺点：违反了设计模式中的 开闭原则 。扩展时需要修改工厂方法
 */
public class Main {
    public static void main(String[] args) {
        // 创建华为手机对象
        Phone phone = PhoneFactory.create("Huawei");
        phone.name();
        phone.produced();

        // 创建索尼手机对象
        Phone phone2 = PhoneFactory.create("Sony");
        phone2.name();
        phone2.produced();
    }
}
