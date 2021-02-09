package com.hat.javaadvance.thread.lock.distributed_lock.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@Service
public class test {
    @Autowired
    RedisLock redisLock;

    /**
     * 测试分布式锁
     */
//    @PostConstruct
    private void testLock(){
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                boolean lock = redisLock.lock("testLock",0);
                if (lock){
                    System.out.println(Thread.currentThread().getName()+"加锁成功 "+ LocalTime.now());
                    try {
                        TimeUnit.SECONDS.sleep(25);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        redisLock.unlock("testLock");
                        System.out.println(Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
                    }
                }else {
                    System.out.println(Thread.currentThread().getName()+"~~~~~~~~加锁失败 "+ LocalTime.now());
                }
            }).start();
        }
    }

    /**
     * 测试分布式锁的可重入特性
     */
//    @PostConstruct
    private void testReentrantLock(){
        try {
            boolean lock = redisLock.lock("testRedisLockKey",0);
            if (lock){
                System.out.println(Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                t2();
                TimeUnit.SECONDS.sleep(30);
            }else {
                System.out.println(Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redisLock.unlock("testRedisLockKey");
            System.out.println(Thread.currentThread().getName()+"释放锁"+ LocalTime.now());

        }
    }

    private void t2(){
        try {
            boolean lock = redisLock.lock("testRedisLockKey",0);
            if (lock){
                System.out.println(Thread.currentThread().getName()+"加锁成功"+ LocalTime.now());
                TimeUnit.SECONDS.sleep(15);
            }else {
                System.out.println(Thread.currentThread().getName()+"加锁失败"+ LocalTime.now());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            redisLock.unlock("testRedisLockKey");
            System.out.println(Thread.currentThread().getName()+"释放锁"+ LocalTime.now());
        }
    }
}
