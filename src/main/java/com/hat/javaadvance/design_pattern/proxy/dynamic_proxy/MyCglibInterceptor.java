package com.hat.javaadvance.design_pattern.proxy.dynamic_proxy;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 自定义方法拦截器，相当于jdk中的InvocationHandler
 */
public class MyCglibInterceptor implements MethodInterceptor {

    /**
     * 相当于jdk动态代理的invoke()方法
     * @param o        被代理的对象
     * @param method   被代理的对象的方法
     * @param objects  被代理的对象的方法的参数
     * @param methodProxy  代理对象
     * @return
     * @throws Throwable
     */
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("实现MethodInterceptor来实现方法增强。。出售手机之前。。。");
        Object invoke = methodProxy.invokeSuper(o, objects);
        System.out.println("实现MethodInterceptor来实现方法增强。。出售手机之后。。。");
        return invoke;
    }
}
