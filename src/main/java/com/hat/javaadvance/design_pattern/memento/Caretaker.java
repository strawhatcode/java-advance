package com.hat.javaadvance.design_pattern.memento;

import java.util.HashMap;
import java.util.Map;

/**
 * 管理者，管理所有备忘录，这里提供一个集合，可以保存很多份不同状态的对象
 */
public class Caretaker {
    private Map<String,Memento> mementos;

    public Caretaker() {
        this.mementos = new HashMap<>();
    }

    public Memento get(String tag) {
        return mementos.get(tag);
    }

    public void add(String tag,Memento memento) {
        mementos.put(tag,memento);
    }
}
