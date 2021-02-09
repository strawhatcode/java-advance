package com.hat.javaadvance.design_pattern.strategy;

/**
 * 抽象策略角色
 * 这个抽象类作为手机销售渠道
 * 如果这个接口只需要实现一个方法则可以使用lambda表达式创建一种新策略
 * 默认实现@FunctionalInterface注解，即该接口是函数式接口
 */
public interface SaleChannel {
    void channel();
}
