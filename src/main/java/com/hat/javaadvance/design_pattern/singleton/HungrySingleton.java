package com.hat.javaadvance.design_pattern.singleton;

/**
 * 单例模式：饿汉式，通过静态变量创建单例；
 * 优点：线程安全，静态变量在类加载中的初始化阶段就运行，且只运行一次。所以不会有线程安全问题
 * 缺点：在类加载时已经创建了对象，如果后面没有用到该对象则会浪费内存
 * 使用场景：轻量级的对象、该对象肯定会被用到时
 */
public class HungrySingleton {
    // 使用静态变量创建一个对象
    private static final HungrySingleton singleton = new HungrySingleton();
    // 构造方法私有，不能让其他对象初始化
    private HungrySingleton(){}
    // 暴露一个获取对象的方法，其他对象只能通过该方法获取该对象
    public static HungrySingleton getSingleton(){
        init();//模拟耗时任务
        return singleton;
    }

    private static void init(){
        // 模拟耗时任务----开始----
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 模拟耗时任务----结束----
    }
}