package com.hat.javaadvance.design_pattern.factory.abstract_factory;

/**
 * 抽象工厂模式：对多个产品进行封装
 *      多个抽象产品（Phone、Computer...）、
 *      多个具体产品（LenovoPhone、LenovoComputer、HuaweiPhone、HuaweiComputer...）、
 *      一个抽象工厂（ElectronicFactory）
 *      多个具体工厂（LenovoElectronicFactory、HuaweiElectronicFactory...）
 * 缺点：抽象工厂中的方法必须得固定，确保后面不会新增，否则扩展时很麻烦，
 *      比如ElectronicFactory目前只能createPhone和createComputer，
 *      如果要新增平板电脑createTablet，就得改抽象工厂类和其子类所有类
 */
public class Main {
    public static void main(String[] args) {
        ElectronicFactory ef = new HuaweiElectronicFactory();
        ef.createPhone().name();
        ef.createPhone().produced();

        ef.createComputer().name();
        ef.createComputer().produced();

        ElectronicFactory ef2 = new LenovoElectronicFactory();
        ef2.createPhone().name();
        ef2.createPhone().produced();

        ef2.createComputer().name();
        ef2.createComputer().produced();
    }
}
