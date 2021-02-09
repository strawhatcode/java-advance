package com.hat.javaadvance.design_pattern.flyweight;

/**
 * 具体享元对象，需要共享的对象，其中name是内部状态，即不会变的内容
 */
public class ResourceManager extends Software {

    public ResourceManager(String name) {
        super(name);
    }

    @Override
    protected void open(DiffForSoftware diff) {
        System.out.print("打开了资源管理器，");
        System.out.println("当前浏览的路径："+diff.getUrl());
    }
}
