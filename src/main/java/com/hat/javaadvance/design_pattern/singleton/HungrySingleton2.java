package com.hat.javaadvance.design_pattern.singleton;

/**
 * 饿汉式，使用静态代码块，与使用静态变量一样
 */
public class HungrySingleton2 {
    private static HungrySingleton2 singleton;

    static {
         singleton = new HungrySingleton2();
    }
    private HungrySingleton2(){};

    public static HungrySingleton2 getSingleton(){
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
