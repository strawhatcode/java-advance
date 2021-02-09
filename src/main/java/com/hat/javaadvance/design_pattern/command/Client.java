package com.hat.javaadvance.design_pattern.command;

/**
 * 命令模式：
 *    将请求封装成对象，使发送请求（调用者）和接受请求（接收者）的对象分开，两者通过一个（命令）来进行沟通
 *    具体命令中有接收者对象，调用者有具体命令对象。调用者通过具体命令对接收者发送命令
 *  优点：
 *      - 调用者与接收者分开，实现对象之间的解耦
 *      - 容易扩展，如果有新的命令实现类，只需要实现抽象命令类
 *      - 容易与其他设计模式混合使用。
 *  缺点：
 *      - 命令很多时实现命令的类也会变得很多
 *  具体场景：
 *      - jdbcTemplate
 */
public class Client {
    public static void main(String[] args) {
        // 创建一个tv命令
        TVCommand tvCommand = new TVCommand(new TV());
        // 创建一个调用者
        Invoker invoker = new Invoker(tvCommand);
        // 使用调用者来对接收者发送命令打开/关闭电视
        invoker.open();
        invoker.stop();

        // 创建一个lamp命令
        LampCommand lampCommand = new LampCommand(new Lamp());
        // 创建一个调用者
        Invoker invoker2 = new Invoker(lampCommand);
        // 使用调用者来对接收者发送命令打开/关闭台灯
        invoker2.open();
        invoker2.stop();
    }
}
