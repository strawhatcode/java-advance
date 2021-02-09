package com.hat.javaadvance.dynamic_proxy.cglib;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib动态代理底层基于ASM。
 * 可以代理接口也可以代理普通的类，也可以使用继承的方式实现动态代理
 * 不能代理private、final修饰符修饰的类
 */
public class CGLIBProxy {
    public static void main(String[] args) {
        // 创建一个代理增强对象
        Enhancer enhancer = new Enhancer();
        // 设置被代理的类
        enhancer.setSuperclass(PersonImpl.class);
        // 创建方法拦截器
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("玩游戏前。。。");
                Object o1 = methodProxy.invokeSuper(o, objects);
                System.out.println("玩游戏后。。。");
                return o1;
            }
        });
        // 创建代理对象
        PersonImpl o = (PersonImpl) enhancer.create();
        o.playing("李四");

        Enhancer enhancer1 = new Enhancer();
        enhancer1.setSuperclass(PersonImpl.class);
        enhancer1.setCallback(new CglibMethodInterceptor());
        PersonImpl o1 = (PersonImpl) enhancer1.create();
        o1.playing("王五");
    }
}
