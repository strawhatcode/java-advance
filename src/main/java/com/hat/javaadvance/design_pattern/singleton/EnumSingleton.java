package com.hat.javaadvance.design_pattern.singleton;

/**
 * 枚举单例模式：
 * 优点：线程安全，效率高，不会被反射破坏，防止被序列化和反序列化破破坏
 * 缺点：
 * 使用场景：单例模式的最优解
 */
public enum EnumSingleton {
    SINGLETON;

    EnumSingleton() {
        init();//模拟耗时任务
    }

    int i = 0;
    // 单例对象的方法
    public void enumDoSomething(){
        System.out.println(i++);
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
