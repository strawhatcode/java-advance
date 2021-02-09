package com.hat.javaadvance.design_pattern.flyweight;
import java.util.concurrent.Executors;

/**
 * 享元模式：
 * 将一些经常使用的对象保存起来，通过共享这些对象来减少对这些对象的创建，
 * 避免创建过多的对象而导致系统的开销过大。
 * 通常享元模式会与工厂模式和单例模式一起使用。
 * 具体应用场景：
 *      数据库连接池
 *      线程池
 *      String常量池
 */
public class Main {
    public static void main(String[] args) {
        SoftwareFactory factory = SoftwareFactory.getInstance();

        Software s1 = factory.getSoftware("manager");
        Software s2 = factory.getSoftware("manager");
        Software s3 = factory.getSoftware("manager");

        s1.open(new DiffForSoftware("/user/m1"));
        s2.open(new DiffForSoftware("/user/m2"));
        s3.open(new DiffForSoftware("/user/m3"));

        System.out.println("------------------------");
        System.out.println("s1对象等于s2:" + (s1 == s2));
        System.out.println("s1对象等于s3:" + (s1 == s3));
        System.out.println("享元对象集合中的对象个数："+factory.getFlyweightSize());
        System.out.println("------------------------");
    }
    
    private static void  aa(){
        Executors.newFixedThreadPool(10);
    }
}
