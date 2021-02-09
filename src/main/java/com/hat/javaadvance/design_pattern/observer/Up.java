package com.hat.javaadvance.design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象目标
 */
public abstract class Up {
    // 粉丝们（观察者们）
    protected List<Fan> fans = new ArrayList<>();

    public void addFan(Fan fan){
        fans.add(fan);
    }

    public void removeFan(Fan fan){
        fans.remove(fan);
    }

    public abstract void publish(String name);

}
