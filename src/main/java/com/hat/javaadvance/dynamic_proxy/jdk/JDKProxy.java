package com.hat.javaadvance.dynamic_proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

/***
 * jdk动态代理是使用反射来实现
 * 必须得创建接口才可以被代理
 */
public class JDKProxy {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Person person = new Owner();
        // 创建一个代理对象
        Person o = (Person) Proxy.newProxyInstance(person.getClass().getClassLoader(),
                person.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    // 代理对象在调用方法前后所做的业务逻辑
                    System.out.println(args1);
                    System.out.println(args1[0]+" 睡觉前。。。。");
                    method.invoke(person, args1);
                    System.out.println(args1[0]+" 睡觉后。。。。");
                    return proxy;
                });
        // 使用代理对象调用方法
        o.sleeping("张三");

        // 创建被代理的对象
        Person p2 = new Owner();
        // 创建自定义的处理代理对象的MyInvokeHandler类，实现InvocationHandler接口
        MyInvokeHandler myInvokeHandler = new MyInvokeHandler(p2);
        // 加载被代理的对象
        Class<?> proxyClass = Proxy.getProxyClass(p2.getClass().getClassLoader(), p2.getClass().getInterfaces());
        // 创建代理对象
        Person o1 = (Person) proxyClass.getConstructor(InvocationHandler.class).newInstance(myInvokeHandler);
        // 给代理对象返回一个处理程序
        Proxy.getInvocationHandler(o1);
        // 通过代理对象调用方法
        o1.eating();

    }
}
