package com.hat.javaadvance.design_pattern.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体聚合对象，实现抽象集合对象接口
 */
public class MyPicture implements Picture {
    // 聚合对象里的一系列对象
    private List<Object> pics;

    public MyPicture() {
        this.pics = new ArrayList<>();
    }

    public MyPicture(List<Object> pics) {
        this.pics = pics;
    }

    @Override
    public void add(Object o) {
        pics.add(o);
    }

    @Override
    public void remove(Object o) {
        pics.remove(o);
    }

    // 获取一个迭代器
    @Override
    public Iterator iterator() {
        return new PicIterator(this.pics);
    }
}
