package com.hat.javaadvance.design_pattern.observer;

/**
 * 具体目标
 */
public class UpAAA extends Up {
    @Override
    public void publish(String name) {
        System.out.println("UpAAA发布了一个视频："+name);
        for (Object f: fans) {
            ((Fan)f).received(name);
        }
    }
}
