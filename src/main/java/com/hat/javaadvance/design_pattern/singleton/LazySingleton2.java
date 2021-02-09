package com.hat.javaadvance.design_pattern.singleton;

/**
 * 懒汉式；给获取对象的方法加锁
 * 在使用到时才创建对象，与饿汉式的差别是懒汉式可以延迟加载，即只有在使用到时才会创建对象
 * 优点：可以延时创建对象，线程安全
 * 缺点：效率慢
 * 使用场景：需要用到的时候才创建对象，可以使用
 */
public class LazySingleton2 {

    private static LazySingleton2 singleton;

    private LazySingleton2() {
    }

    public static synchronized LazySingleton2 getSingleton() {
        if (singleton == null) {
            init();//模拟耗时任务

            singleton = new LazySingleton2();
        }
        return singleton;
    }

    private static void init() {
        // 模拟耗时任务----开始----
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 模拟耗时任务----结束----
    }
}
