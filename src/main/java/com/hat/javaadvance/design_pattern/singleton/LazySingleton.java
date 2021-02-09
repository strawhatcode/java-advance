package com.hat.javaadvance.design_pattern.singleton;

/**
 * 懒汉式；普通用法，不加锁
 *      在使用到时才创建对象，与饿汉式的差别是懒汉式可以延迟加载，即只有在使用到时才会创建对象
 * 优点：可以延时创建对象
 * 缺点：线程不安全
 * 使用场景：需要用到的时候才创建对象，不推荐使用
 */
public class LazySingleton {

    private static LazySingleton singleton;
    private LazySingleton(){}

    public static LazySingleton getSingleton(){
        if (singleton == null){
            init();//模拟耗时任务
            singleton = new LazySingleton();
        }
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
