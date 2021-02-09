package com.hat.javaadvance.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvokeHandler implements InvocationHandler {
    private Object target;

    public MyInvokeHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("在运行方法前所做的操作。。。。");
        method.invoke(target,args);
        System.out.println("在运行方法后所做的操作。。。。");
        return proxy;
    }
}
