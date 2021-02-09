package com.hat.javaadvance.design_pattern.decorator;

/**
 * 具体组件（ConcreteComponent）的封装，实现抽象组件Phone
 * 封装了具体组件共同的特性
 */
public class HuaweiPhone implements Phone {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void wrapper() {
        System.out.println("华为 "+this.name+" 裸机");
    }
}
