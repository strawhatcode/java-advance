package com.hat.javaadvance.thread.threefeature;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发编程线程的三大特性----原子性
 */
public class Atomic {
    private static /*volatile*/ int num = 0;
    private static AtomicInteger atomicNum = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        way3();
    }

    private static void way1() throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                num++;  // 这不是一个原子操作，会出现线程安全问题
                System.out.println("线程：" + Thread.currentThread().getName() + "   " + num);
            }).start();
        }
        TimeUnit.SECONDS.sleep(1L);//等待线程都执行完毕
        System.out.println("num的值：" + num);
    }

    private static void way2() throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                // 加锁保证原子性
                synchronized (Atomic.class) {
                    num++;  // 这不是一个原子操作，会出现线程安全问题
                    System.out.println("线程：" + Thread.currentThread().getName() + "   " + num);
                }
            }).start();
        }
        TimeUnit.SECONDS.sleep(1L);//等待线程都执行完毕
        System.out.println("num的值：" + num);
    }

    private static void way3() throws InterruptedException {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                int num2 = atomicNum.incrementAndGet();// 这不是一个原子操作，会出现线程安全问题
                System.out.println("线程：" + Thread.currentThread().getName() + "   " + num2);
            }).start();
        }
        TimeUnit.SECONDS.sleep(1L);//等待线程都执行完毕
        System.out.println("atomicNum的值：" + atomicNum);
    }
}
