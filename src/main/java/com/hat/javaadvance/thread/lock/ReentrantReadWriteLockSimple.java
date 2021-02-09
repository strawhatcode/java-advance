package com.hat.javaadvance.thread.lock;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockSimple {
    private static int num = 0;
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(); // 创建读写锁
    private static ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();  // 创建读锁
    private static ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();  // 创建写锁

    public static void main(String[] args) {
//        testReadLock();
//        testWriteLock();
//        testReadWriteLock();
        testWriteReadLock();
    }

    private static void testWriteReadLock(){
        new Thread(() -> {
            setNum();  // 写锁的方法
        }).start();

        new Thread(() -> {
            getNum();  // 读锁的方法
        }).start();
    }

    private static void testReadWriteLock(){
        new Thread(() -> {
            getNum();  // 读锁的方法
        }).start();

        new Thread(() -> {
            setNum();  // 写锁的方法
        }).start();
    }

    private static void testWriteLock() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                setNum();
            }).start();
        }
    }

    // 使用写锁的方法
    private static void setNum() {
        try {
            writeLock.lock();
            System.out.println(Thread.currentThread().getName() + "得到写锁，num进行自增：" + num + "，并且站有锁1秒," + LocalTime.now());
            num++;
            TimeUnit.SECONDS.sleep(1L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁，num的值：" + num + "  , " + LocalTime.now());
            writeLock.unlock();
        }
    }

    private static void testReadLock() {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                getNum();
            }).start();
        }
    }


    // 使用读锁的方法
    private static void getNum() {
        try {
            readLock.lock();  // 获取读锁
            System.out.println(Thread.currentThread().getName() + "得到读锁，得到num的值：" + num + "，并且占有锁5秒," + LocalTime.now());
            TimeUnit.SECONDS.sleep(5L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + "释放锁，" + LocalTime.now());
            readLock.unlock();  // 释放写锁
        }
    }
}
