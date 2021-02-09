package com.hat.javaadvance.thread;

import java.util.concurrent.TimeUnit;

public class Thread_ {
    ThreadLocal<Integer> local = new ThreadLocal<>(); // 创建一个ThreadLocal对象，泛型是存放的对象类型
    public static void main(String[] args) throws InterruptedException {
//        threadPriority(); // 测试线程优先级
//        threadYeild();   // 测试线程yeild()方法
//        daemonThread();  // 测试守护线程
//        ThreadInterrupt();  // 测试线程中断
        Thread_ thread_ = new Thread_();
        thread_.threadLocal();
    }

    private void threadLocal(){
        local.set(99); // 当前线程(main)设置一个线程本地变量
        Thread thread = new Thread(() -> {
            Integer integer = local.get();  // thread线程获取本地变量(没设置过，所以null)
            System.out.println("thread:"+integer);
            local.set(888);  // 设置thread线程的本地变量
            Integer integer1 = local.get();  // 888
            System.out.println("thread after:"+integer1);
        });
        thread.start();  // 启动thread线程
        Integer integer = local.get();  // 读取当前线程(main)本地变量 99
        System.out.println("main:"+integer);
        
    }


    private static void ThreadInterrupt() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            System.out.println("进入线程~~");
            try {
                // 打印1到10，没打印一次休眠300ms
                while (i < 10) {
                    System.out.println(i);
                    i++;
                    TimeUnit.MILLISECONDS.sleep(300L);
                }
                System.out.println("耗时任务完成~~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // 线程被中断时，捕获了中断异常且未在try{}内的代码会接着执行
            System.out.println("线程执行完毕~~");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(1L);
        thread.interrupt();  // 1s后中断thread线程
    }

    private static void daemonThread() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            System.out.println("进入守护线程~~");
            try {
                // 打印1到10，没打印一次休眠300ms
                while (i < 10) {
                    System.out.println(i);
                    i++;
                    TimeUnit.MILLISECONDS.sleep(300L);
                }
                System.out.println("耗时任务完成~~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("守护线程执行完毕~~");
        });
        thread.setDaemon(true); // 设置该线程为守护线程，必须再启动前设置，否则会抛出异常
        thread.start();  // 启动守护线程
        TimeUnit.SECONDS.sleep(1L);  // 主线程休眠1s后执行完毕
        System.out.println("主线程执行完毕~~~");
    }

    private static void threadYeild() throws InterruptedException {
        Thread thread = new Thread(() -> {
            int i = 0;
            System.out.println("进入线程~~");
            try {
                // 打印1到10，没打印一次休眠100ms
                while (i<10){
                    System.out.println(i);
                    i++;
                    TimeUnit.MILLISECONDS.sleep(100L);
                }
                System.out.println("耗时任务完成~~~");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程执行完毕~~");
        });
        thread.start();
        TimeUnit.MILLISECONDS.sleep(500L);
        // 线程启动后延迟500ms调用yield()方法放弃CPU时间片重回RUNNABLE状态再次竞争CPU时间片
        thread.yield();
        System.out.println("thread放弃CPU时间片");
    }

    // 线程的优先级设置
    private static void threadPriority(){
        Thread thread = new Thread(() -> {
            System.out.println("进入");
        });
        System.out.println(thread.getPriority());// 获取优先级

        thread.setPriority(8);  // 设置优先级
        int priority = thread.getPriority();
        System.out.println(priority);
        thread.start();
        thread.setPriority(3);
        int priority1 = thread.getPriority();
        System.out.println(priority1);
    }
}
