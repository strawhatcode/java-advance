package com.hat.javaadvance.thread.lock;

import org.openjdk.jol.info.ClassLayout;

public class ObjectLayout {
    public static void main(String[] args) throws InterruptedException {
//        unLocked();
//        biasedLock();
//        lightweightLocked();
        heavyweightLocked();
    }

    // 重量级锁
    private static void heavyweightLocked() throws InterruptedException {
        MyObject o = new MyObject();
        // 1. 无锁状态
        System.out.println("锁前："+Thread.currentThread().getName());
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        Thread thread1 = new Thread(() -> {
            // 2. thread1线程竞争o对象锁，o对象从无锁升级为轻量级锁
            synchronized (o) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("thread1释放锁");
        });
        thread1.start();
        Thread.sleep(1000);
        System.out.println("线程thread1锁定中");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        // 3. 自旋等待thread1线程释放锁，o对象锁从轻量级锁升级为重量级锁
        synchronized (o){
            // 4. main线程获取到重量级锁
            System.out.println("线程main锁定中");
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        // 5. main线程释放锁，o对象锁还是持有重量级锁
        System.out.println("线程main释放锁后");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    // 轻量级锁
    private static void lightweightLocked(){
        MyObject o = new MyObject();
        System.out.println("锁之前");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println("进入锁");
            // 对象头
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println("锁之后");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    // 偏向锁
    private static void biasedLock() throws InterruptedException {
        // 睡眠5秒，JVM的偏向锁默认有4秒的延时，4秒后才会启用偏向锁
        // 也可以使用JVM参数 -XX:BiasedLockingStartupDelay=0 设置延时为0
        Thread.sleep(5000);
        MyObject o = new MyObject();
        System.out.println("锁之前");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        synchronized (o){
            System.out.println("进入锁");
            // 对象头
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
        System.out.println("锁之后");
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    // 无锁状态的对象头
    private static void unLocked(){
        MyObject o = new MyObject();
        System.out.println("对象 hash 之前");
        // 对象头
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        int hashcode = o.hashCode();
        System.out.println("对象 hash 之后，hash code："+hashcode+",  hex："+Integer.toHexString(hashcode));

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
