package com.hat.javaadvance.thread.lock;


import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.locks.ReentrantLock;


public class ThreeFeature {
    private static boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        ThreeFeature threeFeature = new ThreeFeature();
        threeFeature.testVisibility();
        Thread.sleep(1000);
        String s3 = ClassLayout.parseInstance(threeFeature).toPrintable();
        System.out.println(s3);
        ReentrantLock reentrantLock = new ReentrantLock(false);

    }

    private void testVisibility() throws InterruptedException {
        String s = ClassLayout.parseInstance(this).toPrintable();
        System.out.println(s);
        synchronized (this){
            String s2 = ClassLayout.parseInstance(this).toPrintable();
            System.out.println(s2);
        }
    }

    private synchronized void A(){
        System.out.println("A同步方法");
        B(); // 同步方法内调用另外一个同步方法（锁内调用一个加锁的方法）
    }

    private synchronized void B(){
        System.out.println("B同步方法");
    }
}
