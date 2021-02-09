package com.hat.javaadvance.design_pattern.singleton;

/**
 * 饿汉式；双重检测
 * 优点：
 * 缺点：
 * 使用场景：
 */
public class DoubleCheckSingleton {
    private static volatile DoubleCheckSingleton singleton;
    private DoubleCheckSingleton(){}

    public static DoubleCheckSingleton getSingleton(){
        // 第一重检测
        if (singleton == null){
            // 第二重检测
            synchronized (DoubleCheckSingleton.class){
                if (singleton == null){
                    init(); //模拟耗时任务
                    singleton = new DoubleCheckSingleton();
                }
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
