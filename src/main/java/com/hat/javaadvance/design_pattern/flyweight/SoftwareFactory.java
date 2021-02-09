package com.hat.javaadvance.design_pattern.flyweight;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建和管理享元对象的工厂类（单例）
 */
public class SoftwareFactory {
    // 使用单例模式创建工厂
    private static SoftwareFactory factory = new SoftwareFactory();
    // 私有构造器
    private SoftwareFactory() {
    }
    // 提供给外部获取工厂
    public static SoftwareFactory getInstance(){
        return factory;
    }

    // 存放享元对象的集合
    private Map<String,Software> flyweights = new ConcurrentHashMap<>();

    // 获取对象
    public Software getSoftware(String name){
        // 如果存放享元对象的集合没有该对象则创建，并且存入集合
        if (!flyweights.containsKey(name)){
            ResourceManager manager = new ResourceManager(name);
            flyweights.put(name,manager);
            return manager;
        }
        return flyweights.get(name);
    }

    // 获取享元对象集合中的对象个数
    public int getFlyweightSize(){
        return flyweights.size();
    }
}
