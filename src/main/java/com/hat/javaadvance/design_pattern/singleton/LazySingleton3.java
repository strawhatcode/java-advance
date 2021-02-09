package com.hat.javaadvance.design_pattern.singleton;

/**
 * 懒汉式；使用同步块给具体创建对象的逻辑加锁
 *      在使用到时才创建对象，与饿汉式的差别是懒汉式可以延迟加载，即只有在使用到时才会创建对象
 * 优点：可以延时创建对象
 * 缺点：线程不安全、效率慢
 * 使用场景：需要用到的时候才创建对象，不推荐使用
 */
public class LazySingleton3 {

    private static volatile LazySingleton3 singleton;
    private LazySingleton3(){}

    public static LazySingleton3 getSingleton(){
        if (singleton == null){
            // 同步代码块
            synchronized (LazySingleton3.class){
                init();//模拟耗时任务
                singleton = new LazySingleton3();
            }

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
