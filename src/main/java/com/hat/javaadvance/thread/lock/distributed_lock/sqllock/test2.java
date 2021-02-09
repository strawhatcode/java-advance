package com.hat.javaadvance.thread.lock.distributed_lock.sqllock;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

public class test2 {
    private SqlLock lock = new SqlLock("machineB");

    public static void main(String[] args) {
        test2 test2 = new test2();
        test2.testDistributedB();
    }
    // 测试分布式锁
    private void testDistributedB(){
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    boolean b = lock.lock("distributed", 0);
                    if (b) {
                        System.out.println("machineB机器-"+Thread.currentThread().getName()+"-testDistributedB方法成功获得锁"+ LocalTime.now());
                        TimeUnit.SECONDS.sleep(7L);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock("distributed");
                }
            }).start();
        }
    }
}
