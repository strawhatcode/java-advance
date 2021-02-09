package com.hat.javaadvance.design_pattern.adapter.class_adapter;

/**
 * 适配者（Adaptee），即原来的耳机接口类型
 */
public class Earphone {
    // 该耳机的接口是3.5mm
    public String earphoneInput(){
        System.out.println("3.5mm耳机接口的耳机");
        return "3.5mm";
    };
}
