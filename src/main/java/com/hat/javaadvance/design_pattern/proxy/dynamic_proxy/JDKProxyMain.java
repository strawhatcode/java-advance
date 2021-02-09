package com.hat.javaadvance.design_pattern.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/**
 * 动态代理模式：使用JDK实现
 * 使用JDK来实现动态代理的类必须有接口，JDK动态代理是使用反射来获取被被代理类的
 * 由于java语言是不支持多继承的，而jdk创建的代理类是继承了Proxy类，
 * 所以不能再有继承，只能使用接口来实现
 * 具体场景：
 *      spring AOP
 */
public class JDKProxyMain {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        PhoneSales sales = new OfflinePhoneSales();
        /** 使用Proxy的newProxyInstance创建一个代理对象，
         * 接受的参数：类装载器、类的接口、方法的增强（InvocationHandler）
         * 这个例子使用lambda表达式来实现InvocationHandler
         */
        PhoneSales o = (PhoneSales) Proxy.newProxyInstance(sales.getClass().getClassLoader(),
                sales.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("售出手机之前。.....");
                    method.invoke(sales, args1);
                    System.out.println("售出手机之后。。。。。");
                    return proxy;
                });
        // 使用代理对象调用被代理对象的方法
        o.sales();

        System.out.println("------------------------------------");
        /**
         * 使用自定义的InvocationHandler来实现被代理对象的方法增强
         */
        PhoneSales sales2 = new OfflinePhoneSales();
        // 根据被代理对象的类加载器（ClassLoader）和接口（Interfaces）来加载代理对象
        Class<?> proxyClass = Proxy.getProxyClass(sales2.getClass().getClassLoader(),
                sales.getClass().getInterfaces());
        // 获取代理对象的构造器，并且新增自定义的InvocationHandler实例MyJDKInvocationHandler
        PhoneSales s = (PhoneSales) proxyClass.getConstructor(InvocationHandler.class).newInstance(new MyJDKInvocationHandler(sales2));
        s.sales();
    }
}
