package com.hat.javaadvance.thread.lock;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSimple {
    static ReentrantLock fairLock = new ReentrantLock(); // true为公平锁
    private static int flag = 0;

    public static void main(String[] args) throws InterruptedException {
//        testInterrupt();
//        fairLock();
//        condition();
//        multiCondition();
        testTryLock();
    }

    private static void testTryLock() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        new Thread(() -> {
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName() + "得到锁，并且需要执行5秒， "+LocalTime.now());
                TimeUnit.SECONDS.sleep(10L); // 该线程占有锁5秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println(Thread.currentThread().getName()+"执行完毕，"+LocalTime.now());
            }
        }).start();
        boolean b; // 记录是否获取锁
        TimeUnit.SECONDS.sleep(1L);  //主线程休眠1秒，确保上面线程已经占有锁
        try {
//            b = lock.tryLock(); // 尝试获取锁，立刻返回，成功获取则返回true，失败返回false
            b = lock.tryLock(6L,TimeUnit.SECONDS); // 尝试获取锁，等待6秒后返回，成功获取则返回true，失败返回false
            System.out.println(Thread.currentThread().getName()+ "获取锁情况:"+b + "，"+LocalTime.now());
        }finally {
            lock.unlock(); // 释放锁
            System.out.println(Thread.currentThread().getName()+ "释放锁");
        }
    }

    private static void multiCondition(){
        ReentrantLock lock = new ReentrantLock();
        Condition c0 = lock.newCondition();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    lock.lock();
                    if (flag != 0){
                        c0.await();  // 条件0等待
                    }
                    System.out.println(Thread.currentThread().getName()+"执行~~");
                    flag = 1;  // 下次线程1执行
                    c1.signalAll(); // 唤醒线程1
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    lock.lock();
                    if (flag != 1){
                        c1.await();  // 条件1等待
                    }
                    System.out.println(Thread.currentThread().getName()+"执行~~");
                    flag = 2;  // 下次线程2执行
                    c2.signalAll(); // 唤醒线程2
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();

        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                try {
                    lock.lock();
                    if (flag != 2){
                        c2.await(); // 条件2等待
                    }
                    System.out.println(Thread.currentThread().getName()+"执行~~");
                    flag = 0;  // 下次线程0执行
                    c0.signalAll(); // 唤醒线程0
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }

    private static void condition() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread t1 = new Thread(() -> {
            System.out.println("进入" + Thread.currentThread().getName());
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+"进入等待状态，"+LocalTime.now());
                condition.await(); // 线程进入等待状态
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
            System.out.println(Thread.currentThread().getName()+"被唤醒，"+LocalTime.now());
        });
        t1.start();
        TimeUnit.SECONDS.sleep(3L); // 主线程阻塞3秒
        try {
            lock.lock();
            condition.signalAll(); // 唤醒进入等待状态的线程
        } finally {
            lock.unlock();
        }
    }

    private static void fairLock(){
        myRunnable runnable = new myRunnable();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(runnable);
        }
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
    static class myRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"已启动");
            try {
                fairLock.lock(); // 获取锁
                System.out.println("线程："+Thread.currentThread().getName()+"---得到锁 ");
            } finally {
                fairLock.unlock();
            }
        }
    }

    private static void testInterrupt() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        // 线程1
        Thread t1 = new Thread(() -> {
            try {
                lock.lock(); // 获取锁
                System.out.println("线程" + Thread.currentThread().getName() + "获取到锁，将要执行6秒， " + LocalTime.now());
                TimeUnit.SECONDS.sleep(6L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  // 释放锁
                System.out.println("线程" + Thread.currentThread().getName() + "释放锁， " + LocalTime.now());
            }
        });
        t1.start();
        // 线程2
        Thread t2 = new Thread(() -> {
            try {
                lock.lockInterruptibly();  // 实现等待可中断的方法
                System.out.println("线程" + Thread.currentThread().getName() + "获取到锁 " + LocalTime.now());
                TimeUnit.SECONDS.sleep(10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();  // 释放锁
                System.out.println("线程" + Thread.currentThread().getName() + "释放锁， " + LocalTime.now());
            }
        });
        t2.start();

        TimeUnit.SECONDS.sleep(5L);
        System.out.println("主线程等待了5秒， " + LocalTime.now());
        if (lock.hasQueuedThread(t2)){
            System.out.println("线程2还再等待中，不等了，中断线程2");
            t2.interrupt(); //中断线程2等待获取锁
        }
    }
}
