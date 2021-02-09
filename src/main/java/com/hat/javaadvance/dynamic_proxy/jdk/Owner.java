package com.hat.javaadvance.dynamic_proxy.jdk;

public class Owner implements Person {
    @Override
    public void sleeping(String name) {
        System.out.println(name+"在睡觉");
    }

    @Override
    public void eating() {
        System.out.println("吃饭");

    }

    @Override
    public void playing() {
        System.out.println("玩游戏");

    }
}
