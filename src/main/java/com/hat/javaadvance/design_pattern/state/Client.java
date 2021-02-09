package com.hat.javaadvance.design_pattern.state;

/**
 * 状态模式：
 *  一个对象有多种状态时，把状态都抽象出来成一个个状态对象，这些状态对象都有其自身的行为实现。
 *  状态对象都有一个上下文（环境）作为属性
 *  上下文（环境）都有一个状态对象来表示当前状态
 *  优点：
 *      - 把if...else抽象出来成不同状态，符合对象的单一职责原则
 *      - 有利于程序的扩展，扩展状态时不会影响原有的其他状态
 *  缺点：
 *      - 状态多时类会增多
 *      - 代码的结构会变得更复杂
 *  具体场景:
 *      - Spring State Machine状态机
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        MyThread thread = new MyThread("线程AAA"); // 创建线程（new状态）
        thread.start(); // 启动线程（runnable状态）
        thread.sleep(1000); // 阻塞线程（block状态）
        thread.getCpuResource(); // 获取cpu资源开始执行（running状态）
        thread.destroy(); // 线程执行完毕销毁线程（dead状态）
    }
}
