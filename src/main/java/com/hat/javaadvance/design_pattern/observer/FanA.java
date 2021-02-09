package com.hat.javaadvance.design_pattern.observer;

/**
 * 具体观察者A
 */
public class FanA implements Fan {
    @Override
    public void received(String name) {
        System.out.println("FanA收到up主AAA的视频推送："+name);
    }
}
