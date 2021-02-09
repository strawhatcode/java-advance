package com.hat.javaadvance.thread.lock.distributed_lock.zookeeperlock;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class LockTest {
    private static ZkLock lock = new ZkLock("machineAAAAA");

    public static void main(String[] args) {
//        testReentrant();
        testDistributed();
    }

    private static void testDistributed(){
        ZkLock lockA = new ZkLock("machine__AAAA");
        ZkLock lockB = new ZkLock("machine__BBBB");
        for (int i = 0; i < 3; i++) {
            if (i % 2 == 0){
                new Thread(() -> {
                    try {
                        boolean b = lockA.lock("lokkk");
                        if (b){
                            System.out.println("machine__AAAA---"+Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                            TimeUnit.SECONDS.sleep(5L);
                        }else {
                            System.out.println("machine__AAAA---"+Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lockA.unlock("lokkk");
                        System.out.println("machine__AAAA---"+Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
                    }
                }).start();
            }else {
                new Thread(() -> {
                    try {
                        boolean b = lockB.lock("lokkk");
                        if (b){
                            System.out.println("machine__BBBB---"+Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                            TimeUnit.SECONDS.sleep(5L);
                        }else {
                            System.out.println("machine__BBBB---"+Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lockB.unlock("lokkk");
                        System.out.println("machine__BBBB---"+Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
                    }
                }).start();
            }

        }

    }

    private static void testReentrant(){
        try {
            boolean b = lock.lock("keykey");
            if (b){
                System.out.println(Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                testReentrant2();
                TimeUnit.SECONDS.sleep(10L);
            }else {
                System.out.println(Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock("keykey");
            System.out.println(Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
        }
    }

    private static void testReentrant2(){
        try {
            boolean b = lock.lock("keykey");
            if (b){
                System.out.println("testReentrant2"+Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                testReentrant3();
            }else {
                System.out.println("testReentrant2"+Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
            }

        } finally {
            lock.unlock("keykey");
            System.out.println("testReentrant2"+Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
        }
    }

    private static void testReentrant3(){
        try {
            boolean b = lock.lock("keykey");
            if (b){
                System.out.println("testReentrant3"+Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                TimeUnit.SECONDS.sleep(5L);
            }else {
                System.out.println("testReentrant3"+Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock("keykey");
            System.out.println("testReentrant3"+Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
        }
    }

    private void testCurator(){
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", new RetryNTimes(3, 1000));
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client,"/testlock");
        new Thread(() -> {
            try {
                lock.acquire();
                System.out.println(Thread.currentThread().getName()+"成功获取锁");
                lock.acquire();
                System.out.println(Thread.currentThread().getName()+"成功获取锁");
                TimeUnit.SECONDS.sleep(200);
                lock.release();
                System.out.println(Thread.currentThread().getName()+"释放锁");
                TimeUnit.SECONDS.sleep(30);
//                lock.release();
                System.out.println(Thread.currentThread().getName()+"释放锁");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
//        new Thread(() -> {
//            try {
//                lock.acquire();
//                System.out.println(Thread.currentThread().getName()+"成功获取锁");
//                TimeUnit.SECONDS.sleep(10);
//                lock.release();
//                System.out.println(Thread.currentThread().getName()+"释放锁");
//
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }).start();
    }
}
