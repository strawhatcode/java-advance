package com.hat.javaadvance.design_pattern.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义代理对象的调用接口
 */
public class MyJDKInvocationHandler implements InvocationHandler {
    PhoneSales sales;

    public MyJDKInvocationHandler(PhoneSales sales) {
        this.sales = sales;
    }

    /**
     * 实现被代理类方法的增强
     * @param proxy  代理类
     * @param method  被代理的类的方法
     * @param args   被代理的类的方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("实现InvocationHandler来实现增强。。售出手机之前。.....");
        method.invoke(sales, args);
        System.out.println("实现InvocationHandler来实现增强。。售出手机之后。。。。。");
        return proxy;
    }
}
