package com.hat.javaadvance.design_pattern.mediator;

/**
 * 抽象中介者，运营商
 */
public interface Operators {
    // 注册
    void register(User user);
    // 转发
    void relay(User from,User to);
}
