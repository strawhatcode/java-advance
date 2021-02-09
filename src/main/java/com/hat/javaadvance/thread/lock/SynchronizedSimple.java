package com.hat.javaadvance.thread.lock;

import java.time.LocalTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class  SynchronizedSimple {
    public static void main(String[] args) {
//        testObjectLock();
//        testMultiObjectLock();
        testMultiClassLock();
    }

    private static void testMultiClassLock(){
        SynchronizedSimple simple1 = new SynchronizedSimple();
        SynchronizedSimple simple2 = new SynchronizedSimple();
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("--------------------------------测试多个对象的类锁--------------------------------");
        pool.submit(() -> {
            try {
                // 调用simple1对象的 类锁 同步代码块
                simple1.lockClass("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                // 调用simple1对象的 类锁 静态同步方法
                simple1.staticMethod("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                // 调用simple2对象的 类锁 同步代码块
                simple2.lockClass("simple2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                // 调用simple2对象的 类锁 静态同步方法
                simple2.staticMethod("simple2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void testMultiObjectLock(){
        SynchronizedSimple simple1 = new SynchronizedSimple();
        SynchronizedSimple simple2 = new SynchronizedSimple();
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("--------------------------------测试多个对象的对象锁--------------------------------");
        pool.submit(() -> {
            try {
                // 调用simple1对象的 对象锁 同步代码块
                simple1.lockObject("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                // 调用simple1对象的 对象锁 同步方法
                simple1.syncMethod("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                // 调用simple2对象的 对象锁 同步代码块
                simple2.lockObject("simple2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                // 调用simple2对象的 对象锁 同步方法
                simple2.syncMethod("simple2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    private static void testObjectLock(){
        SynchronizedSimple simple1 = new SynchronizedSimple();
        SynchronizedSimple simple2 = new SynchronizedSimple();
        ExecutorService pool = Executors.newCachedThreadPool();
        System.out.println("--------------------------------测试单一对象的对象锁与类锁--------------------------------");
        pool.submit(() -> {
            try {
                // 调用simple1对象的 对象锁 同步代码块
                simple1.lockObject("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        pool.submit(() -> {
            try {
                // 调用simple1对象的 类锁 同步代码块
                simple1.lockClass("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                // 调用simple1对象的 对象锁 同步方法
                simple1.syncMethod("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            // 调用simple1对象的 普通方法
            simple1.normalMethod("simple1");

        });
        pool.submit(() -> {
            try {
                // 调用simple1对象的 类锁 静态同步方法
                simple1.staticMethod("simple1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    // 对象锁，占有对象锁15秒
    private void lockObject(String obj) throws InterruptedException {
        synchronized (this){
            System.out.println(Thread.currentThread().getName()+"：lockObject()同步代码块进入---对象："+obj +"---"+ LocalTime.now()+"---15s");
            TimeUnit.SECONDS.sleep(15L);
            System.out.println(Thread.currentThread().getName()+"：lockObject()同步代码块完成---对象："+obj +"---"+ LocalTime.now());
        }
    }
    // 类锁，占有类锁15秒
    private void lockClass(String obj) throws InterruptedException {
        synchronized (SynchronizedSimple.class){
            System.out.println(Thread.currentThread().getName()+"：lockClass()同步代码块进入---类："+obj +"---"+ LocalTime.now()+"---15s");
            TimeUnit.SECONDS.sleep(15L);
            System.out.println(Thread.currentThread().getName()+"：lockClass()同步代码块完成---类："+obj +"---"+ LocalTime.now());
        }
    }
    // 对象锁，占有对象锁5秒
    private synchronized void syncMethod(String obj) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"：syncMethod()同步方法进入---对象："+obj +"---"+ LocalTime.now()+"---5s");
        TimeUnit.SECONDS.sleep(5L);
        System.out.println(Thread.currentThread().getName()+"：syncMethod()同步方法完成---对象："+obj +"---"+ LocalTime.now());
    }
    // 无锁
    private void normalMethod(String obj){
        System.out.println(Thread.currentThread().getName()+"：normalMethod()普通方法："+obj +"---"+ LocalTime.now());
    }
    // 类锁，占有类锁10秒
    private synchronized static void staticMethod(String obj) throws InterruptedException {
        System.out.println(Thread.currentThread().getName()+"：staticMethod()同步方法进入---静态(类)："+obj +"---"+ LocalTime.now()+"---10s");
        TimeUnit.SECONDS.sleep(10L);
        System.out.println(Thread.currentThread().getName()+"：staticMethod()同步方法完成---静态(类)："+obj +"---"+ LocalTime.now());
    }
}
