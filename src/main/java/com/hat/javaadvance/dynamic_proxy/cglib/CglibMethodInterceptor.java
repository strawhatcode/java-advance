package com.hat.javaadvance.dynamic_proxy.cglib;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMethodInterceptor implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("方法调用前。。。。。");
        Object o1 = methodProxy.invokeSuper(o, objects);
        System.out.println("方法调用后。。。。。");
        return o1;
    }
}
