package com.hat.javaadvance.thread.lock.distributed_lock.sqllock;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class test {
    private SqlLock lock = new SqlLock("machineA");

    public static void main(String[] args) {

        test test = new test();
//        test.testReentrant();
        test.testDistributedA();
    }

    // 测试分布式锁
    private void testDistributedA(){
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    boolean b = lock.lock("distributed", 0);
                    if (b) {
                        System.out.println("machineA机器-"+Thread.currentThread().getName()+"-testDistributedA方法成功获得锁"+ LocalTime.now());
                        TimeUnit.SECONDS.sleep(10L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock("distributed");
                }
            }).start();
        }
    }

    // 测试重入锁
    private void testReentrant() {
        try {
            boolean b = lock.lock("reentrantLock", 1000);
            if (b) {
                System.out.println("testReentrant方法成功获得锁");
                testReentrant2();
            }
        } finally {
            lock.unlock("reentrantLock");
        }

    }
    // 测试重入锁
    private void testReentrant2() {
        try {
            boolean b = lock.lock("reentrantLock", 1000);
            if (b) {
                System.out.println("testReentrant2方法成功获得锁");
                TimeUnit.SECONDS.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock("reentrantLock");
        }

    }

    // 测试悲观锁
    private void testLock() {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    boolean b = lock.lock("objTTT", 1000);
                    if (b) {
                        System.out.println(Thread.currentThread().getName() + "获得锁");
                        if (finalI % 3 == 0) {
                            TimeUnit.SECONDS.sleep(3);
                        }
                    } else {
                        System.out.println(Thread.currentThread().getName() + "获得锁失败");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("----------" + Thread.currentThread().getName() + "释放锁");
                    lock.unlock("objTTT");
                }
            }).start();
        }
    }
}
