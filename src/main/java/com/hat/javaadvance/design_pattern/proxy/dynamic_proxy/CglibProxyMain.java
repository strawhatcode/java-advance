package com.hat.javaadvance.design_pattern.proxy.dynamic_proxy;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * 动态代理模式：使用cglib实现
 * cglib动态代理底层基于ASM字节码技术
 * 与jdk不同的是，cglib的被代理对象可以是普通类，也可以是继承，
 * 不过被代理对象不能是私有（private）的或者使用final关键字修饰
 * 具体场景：
 *      spring AOP
 */
public class CglibProxyMain {
    public static void main(String[] args) {
        // 创建一个增强器
        Enhancer enhancer = new Enhancer();
        // 设置超类，即被代理的类
        enhancer.setSuperclass(OnlinePhoneSale.class);
        // 设置方法拦截器，即通过该对象来调用真实对象的方法，并实现方法的增强
        enhancer.setCallback(new MyCglibInterceptor());
        // 创建代理类
        OnlinePhoneSale o = (OnlinePhoneSale) enhancer.create();
        // 使用代理类调用真实对象的方法
        o.sale();

        System.out.println("----------------------------");

        Enhancer e2 = new Enhancer();
        e2.setSuperclass(OnlinePhoneSale.class);
        // 使用lambda表达式来设置方法拦截器
        e2.setCallback((MethodInterceptor) (o1, method, objects, methodProxy) -> {
            System.out.println("实现MethodInterceptor来实现方法增强(lambda)。。出售手机之前。。。");
            Object invoke = methodProxy.invokeSuper(o1, objects);
            System.out.println("实现MethodInterceptor来实现方法增强(lambda)。。出售手机之后。。。");
            return invoke;
        });
        OnlinePhoneSale o1 = (OnlinePhoneSale) e2.create();
        o1.sale();
    }
}
