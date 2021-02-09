package com.hat.javaadvance.design_pattern.observer;

/**
 * 具体观察者B
 */
public class FanB implements Fan {
    @Override
    public void received(String name) {
        System.out.println("FanB收到up主AAA的视频推送："+name);
    }
}
