package com.hat.javaadvance.design_pattern.iterator;

import java.util.List;

/**
 * 具体迭代器，初始化索引，带有一个需要遍历的集合对象
 */
public class PicIterator implements Iterator {
    private List<Object> list;
    private int index=-1;

    public PicIterator(List<Object> list) {
        this.list = list;
    }

    @Override
    public Object first() {
        return list.get(0);
    }

    // 先索引+1，然后判断索引有没有超出列表最大长度，如果有内容返回true，否则false
    @Override
    public boolean hasNext() {
        this.index++;
        if (index >= list.size()) {
            return false;
        }else {
            return true;
        }
    }

    //获取下一项的值
    @Override
    public Object next() {
        return list.get(index);
    }
}
