package com.hat.javaadvance.design_pattern.proxy.dynamic_proxy;

/**
 * 真实主题（realSuject），被代理的真实对象
 */
public class OfflinePhoneSales implements PhoneSales {
    @Override
    public void sales() {
        System.out.println("线下实体店售出手机。。。。。。");
    }
}
