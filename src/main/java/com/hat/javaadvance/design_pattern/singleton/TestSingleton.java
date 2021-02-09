package com.hat.javaadvance.design_pattern.singleton;


/**
 * 单例模式：在一个程序里，该对象只创建一次，即只有一个对象
 * 具体场景：
 *      spring种创建bean，创建的bean默认是单例模式，也是配合@Scope注解使用prototype模式
 */
public class TestSingleton {
    public static void main(String[] args) {
//        testHungry();
//        testLazy();
//        testLazy2();
//        testLazy3();
//        testDoubleCheck();
//        testStaticInner();
        testEnum();
//        testTime();

    }

    public static void testTime(){
        long l = System.currentTimeMillis();
        HungrySingleton singleton = HungrySingleton.getSingleton();
        System.out.println(Thread.currentThread().getName() + "：" +
                "HungrySingleton's hashcode：" + singleton.hashCode());
        System.out.println("testHungry耗时："+(System.currentTimeMillis() - l)+"ms");

        l = System.currentTimeMillis();
        HungrySingleton2 singleton2 = HungrySingleton2.getSingleton();
        System.out.println(Thread.currentThread().getName() + "：" +
                "HungrySingleton2's hashcode：" + singleton2.hashCode());
        System.out.println("testHungry2耗时："+(System.currentTimeMillis() - l)+"ms");

        l = System.currentTimeMillis();
        LazySingleton singleton3 = LazySingleton.getSingleton();
        System.out.println(Thread.currentThread().getName() + "：" +
                "LazySingleton's hashcode：" + singleton3.hashCode());
        System.out.println("testLazy耗时："+(System.currentTimeMillis() - l)+"ms");


        l = System.currentTimeMillis();
        LazySingleton2 singleton4 = LazySingleton2.getSingleton();
        System.out.println(Thread.currentThread().getName() + "：" +
                "LazySingleton2's hashcode：" + singleton4.hashCode());
        System.out.println("testLazy2耗时："+(System.currentTimeMillis() - l)+"ms");


        l = System.currentTimeMillis();
        LazySingleton3 singleton5 = LazySingleton3.getSingleton();
        System.out.println(Thread.currentThread().getName() + "：" +
                "LazySingleton3's hashcode：" + singleton5.hashCode());
        System.out.println("testLazy3耗时："+(System.currentTimeMillis() - l)+"ms");


        l = System.currentTimeMillis();
        DoubleCheckSingleton singleton6 = DoubleCheckSingleton.getSingleton();
        System.out.println(Thread.currentThread().getName() + "：" +
                "DoubleCheckSingleton's hashcode：" + singleton6.hashCode());
        System.out.println("testDoubleCheck耗时："+(System.currentTimeMillis() - l)+"ms");


        l = System.currentTimeMillis();
        StaticInnerSingleton singleton7 = StaticInnerSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + "：" +
                "StaticInnerSingleton's hashcode：" + singleton7.hashCode());
        System.out.println("testStaticInner耗时："+(System.currentTimeMillis() - l)+"ms");


        l = System.currentTimeMillis();
        EnumSingleton singleton8 = EnumSingleton.SINGLETON;
        System.out.println(Thread.currentThread().getName() + "：" +
                "EnumSingleton's hashcode：" + singleton8.hashCode());
        System.out.println("testEnum耗时："+(System.currentTimeMillis() - l)+"ms");
    }

    /**
     * 测试饿汉式单例模式（静态变量）
     */
    public static void testHungry() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                HungrySingleton singleton = HungrySingleton.getSingleton();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();
        }
    }

    /**
     * 测试饿汉式单例模式（静态代码块）
     */
    public static void testHungry2() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                HungrySingleton2 singleton = HungrySingleton2.getSingleton();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton2's hashcode：" + singleton.hashCode());
            }).start();
        }
    }

    /**
     * 测试懒汉式单例模式（普通用法）
     * 线程不安全
     */
    public static void testLazy() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton singleton = LazySingleton.getSingleton();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();

        }
    }

    /**
     * 测试懒汉式单例模式（同步锁getSingleton方法）
     * 线程安全
     */
    public static void testLazy2() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton2 singleton = LazySingleton2.getSingleton();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();

        }
    }

    /**
     * 测试懒汉式单例模式（同步块锁具体逻辑代码）
     * 线程不安全
     */
    public static void testLazy3() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                LazySingleton3 singleton = LazySingleton3.getSingleton();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();

        }
    }

    /**
     * 测试懒汉式单例模式（双重检测，锁具体逻辑代码）
     * 线程安全
     */
    public static void testDoubleCheck() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                DoubleCheckSingleton singleton = DoubleCheckSingleton.getSingleton();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();

        }
    }

    /**
     * 测试静态内部类
     * 线程安全、延时加载
     */
    public static void testStaticInner() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                StaticInnerSingleton singleton = StaticInnerSingleton.getInstance();
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();

        }
    }

    /**
     * 测试枚举单例模式（使用枚举）
     * 线程安全，效率高，可以防止被反射破坏，防止序列化和反序列化，实现最简单
     */
    public static void testEnum() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                EnumSingleton singleton = EnumSingleton.SINGLETON;
                System.out.println(Thread.currentThread().getName() + "：" +
                        "singleton's hashcode：" + singleton.hashCode());
            }).start();

        }
    }

}
