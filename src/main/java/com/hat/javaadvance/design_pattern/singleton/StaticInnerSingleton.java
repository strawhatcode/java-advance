package com.hat.javaadvance.design_pattern.singleton;

/**
 * 静态内部类模式；
 *      与饿汉式相比，静态内部类在调用getInstance时才会初始化对象，而不是在类的加载中就初始化
 * 优点：线程安全，延时加载。
 * 缺点：
 * 使用场景：
 */
public class StaticInnerSingleton {
    // 静态内部类
    private static class InnerSingleton {
        private static StaticInnerSingleton singleton = new StaticInnerSingleton();
    }

    public static StaticInnerSingleton getInstance() {
        init();//模拟耗时任务

        return InnerSingleton.singleton;
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
