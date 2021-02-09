package com.hat.javaadvance.design_pattern.factory.simple_factory;

/**
 * 手机工厂，该工厂可以创建不同品牌的手机，就像一家手机店，它可以卖多种品牌的手机
 */
public class PhoneFactory {
    public static Phone create(String name){
        Phone p = null;
        if ("Sony".equals(name)){
            p = new SonyPhone();
        }else if ("Huawei".equals(name)){
            p = new HuaweiPhone();
        }else {
            System.out.println("暂时没有 "+name+" 手机");
        }
        return p;
    }
}
