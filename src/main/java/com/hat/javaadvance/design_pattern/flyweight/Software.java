package com.hat.javaadvance.design_pattern.flyweight;

/**
 * 抽象享元对象，name是内部状态，即这个对象的name是不会改变的
 */
public abstract class Software {
    private String name;

    protected Software(String name) {
        this.name = name;
    }

    // 抽象享元对象的共同方法，接受一个非享元对象作为参数，即外部状态，也就是可能会变的内容
    abstract protected void open(DiffForSoftware diff);
}
