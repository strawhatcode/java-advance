package com.hat.javaadvance.design_pattern.factory.factory_method;

/**
 * 工厂方法模式；对单一产品进行封装
 *      一个抽象产品（Phone）、
 *      多个具体产品（SonyPhone、HuaweiPhone...）、
 *      一个抽象工厂（PhoneFactory）、
 *      多个具体工厂（SonyFactory、HuaweiFactory...）、
 *
 * 一个抽象产品（phone）工厂（PhoneFactory）,其他具体产品（Huawei、sony）工厂都继承抽象产品工厂（PhoneFactory），
 * 然后这些具体的产品工厂创建具体产品的实例。
 *
 * 优点：符合开闭原则，扩展容易，只需要创建一个产品类（Xiaomi）实现抽象产品接口（phone）
 * 和产品工厂（XiaomiFactory）实现抽象工厂（PhoneFactory）
 */
public class Main {
    public static void main(String[] args) {
        Phone p = new SonyFactory().create();
        p.name();
        p.produced();

        Phone p2 = new HuaweiFactory().create();
        p2.name();
        p2.produced();
    }
}
