package com.hat.javaadvance.design_pattern.iterator;

/**
 * 抽象聚合对象，提供了增删或获取迭代器3个抽象方法
 */
public interface Picture {
    void add(Object o);
    void remove(Object o);
    Iterator iterator();
}
