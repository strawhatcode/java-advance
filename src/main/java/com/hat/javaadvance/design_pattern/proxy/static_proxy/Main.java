package com.hat.javaadvance.design_pattern.proxy.static_proxy;

/**
 * 静态代理：在程序启动完后就已经把java类编译成字节码文件（.class文件）。
 *  代理类充当一个中介的职责，客户端通过代理类来找到真实的对象，并且该代理类还可以在调用真实对象的方法的前后实现其他的业务逻辑
 *  代理模式的结构：
 *      抽象主题（Subject）类：通过接口或抽象类声明真实主题和代理对象实现的业务方法。
 *      真实主题（Real Subject）类：实现了抽象主题中的具体业务，是代理对象所代表的真实对象，是最终要引用的对象。
 *      代理（Proxy）类：提供了与真实主题相同的接口，其内部含有对真实主题的引用，它可以访问、控制或扩展真实主题的功能。
 *   优点：
 *      使用代理模式可以适当的隐藏真实的对象，算是对真实对象的一种保护机制。
 *      可以增强真实对象的实现方法，即在调用真实对象的前后做一些其他处理。
 *      降低程序的耦合性，增加程序的可扩展性。
 *   缺点：
 *      增加系统的复杂度，客户端与目标对象中间多了一层代理类，运行效率会变慢
 */
public class Main {
    public static void main(String[] args) {
        // 使用lamda表达式传入被代理的对象创建一个代理对象
        PhoneSales proxy = new PhoneProxy(() -> {
            System.out.println("线上官方店售出手机。。。。");
        });
        // 调用代理对象的方法
        proxy.sales();

        System.out.println("--------------------------------------");
        // 传入被代理的对象
        OfflinePhoneSales off = new OfflinePhoneSales();
        new PhoneProxy(off).sales();
    }
}
