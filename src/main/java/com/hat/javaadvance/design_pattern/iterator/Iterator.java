package com.hat.javaadvance.design_pattern.iterator;

/**
 * 抽象迭代器，声明了获取第一项（first()）内容的方法、
 * 是否有下一项(hasNext())的方法、
 * 获取下一项的值的方法（next()）
 */
public interface Iterator {
    Object first();
    boolean hasNext();
    Object next();
}
