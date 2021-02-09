package com.hat.javaadvance.thread.threadpool;

import ch.qos.logback.core.util.TimeUtil;
import lombok.SneakyThrows;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;

public class ThreadPool {
    ThreadLocal<String[]> local = new ThreadLocal<>();
    public static void main(String[] args) throws InterruptedException, IOException {
        ThreadPool threadPool = new ThreadPool();
//        threadPool.fixed();
//        threadPool.single();
//        threadPool.cached();
//        threadPool.delay();
//        threadPool.forkJoin();
//        threadPool.poolShutdown();
//        threadPool.pool_();
//        threadPool.submitTask();
        threadPool.reject();
    }

    private void reject(){
        // 使用CallerRunsPolicy拒绝策略的线程池
        // 这种策略会调用当前线程(非线程池线程)来执行被拒绝的任务
        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 20, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(3),new ThreadPoolExecutor.CallerRunsPolicy());

        // 使用AbortPolicy拒绝策略的线程池
        // 这种策略会丢弃被拒绝的任务，并抛出异常
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 20, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(3),new ThreadPoolExecutor.AbortPolicy());

        // 使用DiscardPolicy拒绝策略的线程池
        // 这种策略会丢弃被拒绝的任务，不会抛出异常
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 20, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(3),new ThreadPoolExecutor.DiscardPolicy());

        // 使用DiscardOldestPolicy拒绝策略的线程池
        // 这种策略不会丢弃被拒绝的任务，而是丢弃最先进入任务队列的任务，不会抛出异常
//        ThreadPoolExecutor pool = new ThreadPoolExecutor(1, 2, 20, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(3),new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 10; i++) {
            pool.submit(new myThread3(""+i+i,3+i)); // 每个任务耗时
        }
    }

    private void submitTask() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future future = executorService.submit(new myThread3("aaa", 10));
        while (true){
            if (future.isDone()){
                System.out.println("已执行完成");
                break;
            }else if (future.isCancelled()){
                System.out.println("已被取消");
                break;
            }else{
                System.out.println("线程正在执行中~~~~~~");
            }
            TimeUnit.SECONDS.sleep(2L); // 每两秒监控一次future状态
        }
    }

    private void pool_() throws InterruptedException {
        // 创建一个拥有4个核心线程，5个非核心线程，非核心线程最大空闲时间20秒，无界队列的线程池
        ThreadPoolExecutor pool = new ThreadPoolExecutor(4, 5, 20, TimeUnit.SECONDS,
                new LinkedBlockingQueue<>());
        for (int i = 0; i < 10; i++) {
            pool.submit(new myThread3(""+i+i,12)); // 每个任务都耗时12秒
        }
        TimeUnit.SECONDS.sleep(1L); // 休眠1秒等待提交10个任务
        pool.allowCoreThreadTimeOut(true);  // 设置允许核心线程与非核心线程一样拥有超时销毁机制
        // 每10秒打印一次线程池的状态信息
        while (true){
            System.out.println("-----------------------------------------------------------");
            System.out.println("正在运行的任务数："+pool.getActiveCount());
            System.out.println("当前存活线程数："+pool.getPoolSize());
            System.out.println("已完成的任务数："+pool.getCompletedTaskCount());
            System.out.println("工作队列中的任务数："+pool.getQueue().size());
            TimeUnit.SECONDS.sleep(10L);
        }
    }




    // 线程池调用shutdown()方法
    private void poolShutdown() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new myThread3(""+i+i,i+1));
        }
        TimeUnit.SECONDS.sleep(5);
//        executorService.shutdown();  // 测试shutdown()方法
        List<Runnable> runnables = executorService.shutdownNow();// 测试shutdown()方法
//        executorService.submit(new myThread3("12",12)); // 这里会抛出异常，并且后面的代码不会再执行
//        executorService.submit(new myThread3("13",13));
        runnables.forEach(System.out::println);
    }

    // 抢占式线程池
    private void forkJoin() throws IOException {
        ExecutorService pool = Executors.newWorkStealingPool();
        pool.submit(new myThread3("aaa",5));
        pool.submit(new myThread3("bbb",10));
        pool.submit(new myThread3("ccc",20));
        pool.submit(new myThread3("ddd",5));

        pool.submit(new myThread3("eee",9));
        pool.submit(new myThread3("fff",10));
        pool.submit(new myThread3("ggg",20));
        pool.submit(new myThread3("hhh",5));

        pool.submit(new myThread3("iii",5));
        pool.submit(new myThread3("jjj",10));
        pool.submit(new myThread3("kkk",20));
        pool.submit(new myThread3("lll",5));

        System.in.read();
    }

    // 可延迟执行任务的线程池
    private void delay() {
        ScheduledThreadPoolExecutor pool = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(3);
        System.out.println("开始时间:"+ LocalDateTime.now().toString());
        pool.scheduleAtFixedRate(new myThread2("ccc"),3L,3,TimeUnit.SECONDS);
        pool.scheduleWithFixedDelay(new myThread2("ddd"),12L,5,TimeUnit.SECONDS);
        pool.schedule(new myThread2("aaa"),10L,TimeUnit.SECONDS);
        pool.submit(new myThread2("bbb"));
        pool.execute(new myThread2("eee"));

    }

    // 缓存型线程池
    private void cached() throws InterruptedException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        pool.submit(new myThread("aaa"));
        pool.submit(new myThread("bbb"));
        pool.submit(new myThread("ccc"));
        pool.submit(new myThread("ddd"));
        pool.submit(new myThread("eee"));
        pool.submit(new myThread("fff"));
        pool.submit(new myThread("ggg"));
        System.out.println("getActiveCount:"+pool.getActiveCount());
        System.out.println("getCompletedTaskCount:"+pool.getCompletedTaskCount());
        System.out.println("getTaskCount:"+pool.getTaskCount());
        System.out.println("getLargestPoolSize:"+pool.getLargestPoolSize());
        System.out.println("getCorePoolSize:"+pool.getCorePoolSize());
        System.out.println("getMaximumPoolSize:"+pool.getMaximumPoolSize());
        System.out.println("getPoolSize:"+pool.getPoolSize());
        System.out.println("getQueuesize:"+pool.getQueue().size());
        while (pool.getPoolSize() > 0){
            TimeUnit.SECONDS.sleep(20L);
            System.out.println("----------------------------------------------------------------");
            System.out.println("getActiveCount:"+pool.getActiveCount());
            System.out.println("getCompletedTaskCount:"+pool.getCompletedTaskCount());
            System.out.println("getTaskCount:"+pool.getTaskCount());
            System.out.println("getLargestPoolSize:"+pool.getLargestPoolSize());
            System.out.println("getCorePoolSize:"+pool.getCorePoolSize());
            System.out.println("getMaximumPoolSize:"+pool.getMaximumPoolSize());
            System.out.println("getPoolSize:"+pool.getPoolSize());
            System.out.println("getQueuesize:"+pool.getQueue().size());
        }
        pool.submit(new myThread("hhh"));
        System.out.println("----------------------------------------------------------------");
        System.out.println("getActiveCount:"+pool.getActiveCount());
        System.out.println("getCompletedTaskCount:"+pool.getCompletedTaskCount());
        System.out.println("getTaskCount:"+pool.getTaskCount());
        System.out.println("getLargestPoolSize:"+pool.getLargestPoolSize());
        System.out.println("getCorePoolSize:"+pool.getCorePoolSize());
        System.out.println("getMaximumPoolSize:"+pool.getMaximumPoolSize());
        System.out.println("getPoolSize:"+pool.getPoolSize());
        System.out.println("getQueuesize:"+pool.getQueue().size());

    }

    // 只有一个线程的线程池
    private void single() throws InterruptedException {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        pool.submit(new myThread("aaa"));
        pool.submit(new myThread("bbb"));
        pool.submit(new myThread("ccc"));

    }
    // 固定长度线程池
    private void fixed() throws InterruptedException {
        ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        pool.submit(new myThread("aaa"));
        pool.submit(new myThread("bbb"));
        pool.submit(new myThread("ccc"));
        pool.submit(new myThread("ddd"));
        pool.submit(new myThread("eee"));
        pool.submit(new myThread("fff"));
        pool.submit(new myThread("ggg"));
        System.out.println("getActiveCount:"+pool.getActiveCount());
        System.out.println("getCompletedTaskCount:"+pool.getCompletedTaskCount());
        System.out.println("getTaskCount:"+pool.getTaskCount());
        System.out.println("getLargestPoolSize:"+pool.getLargestPoolSize());
        System.out.println("getCorePoolSize:"+pool.getCorePoolSize());
        System.out.println("getMaximumPoolSize:"+pool.getMaximumPoolSize());
        System.out.println("getPoolSize:"+pool.getPoolSize());
        System.out.println("getQueuesize:"+pool.getQueue().size());
        TimeUnit.SECONDS.sleep(25L);

        System.out.println("----------------------------------------------------------------");
        System.out.println("getActiveCount:"+pool.getActiveCount());
        System.out.println("getCompletedTaskCount:"+pool.getCompletedTaskCount());
        System.out.println("getTaskCount:"+pool.getTaskCount());
        System.out.println("getLargestPoolSize:"+pool.getLargestPoolSize());
        System.out.println("getCorePoolSize:"+pool.getCorePoolSize());
        System.out.println("getMaximumPoolSize:"+pool.getMaximumPoolSize());
        System.out.println("getPoolSize:"+pool.getPoolSize());
        System.out.println("getQueuesize:"+pool.getQueue().size());

        TimeUnit.SECONDS.sleep(25L);
        System.out.println("----------------------------------------------------------------");
        System.out.println("getActiveCount:"+pool.getActiveCount());
        System.out.println("getCompletedTaskCount:"+pool.getCompletedTaskCount());
        System.out.println("getTaskCount:"+pool.getTaskCount());
        System.out.println("getLargestPoolSize:"+pool.getLargestPoolSize());
        System.out.println("getCorePoolSize:"+pool.getCorePoolSize());
        System.out.println("getMaximumPoolSize:"+pool.getMaximumPoolSize());
        System.out.println("getPoolSize:"+pool.getPoolSize());
        System.out.println("getQueuesize:"+pool.getQueue().size());

    }

    class myThread implements Runnable{
        private String name;
        public myThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println("任务："+name+"，当前线程："+Thread.currentThread().getName());
            if ("bbb".equals(name)){
                Thread.currentThread().interrupt();
            }
            try {
                TimeUnit.SECONDS.sleep(20L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("任务："+name+"，当前线程："+Thread.currentThread().getName()+"执行完成");
        }
    }

    class myThread2 implements Runnable{
        private String name;
        public myThread2(String name) {
            this.name = name;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("任务："+name+"，当前线程："+Thread.currentThread().getName()+"，当前时间："+LocalDateTime.now().toString());
            if ("ccc".equals(name)){
                TimeUnit.SECONDS.sleep(5L);
            }else if ("ddd".equals(name)){
                TimeUnit.SECONDS.sleep(7L);
            }
            System.out.println("任务："+name+"，当前线程："+Thread.currentThread().getName()+"执行完成，当前时间："+LocalDateTime.now().toString());
        }
    }

    class myThread3 implements Runnable{
        private String name;
        private long time;
        public myThread3(String name,long time) {
            this.name = name;
            this.time = time;
        }

        @SneakyThrows
        @Override
        public void run() {
            System.out.println("当前线程："+Thread.currentThread().getName()+" 持有任务："+name+"，需执行时间："+time);
            TimeUnit.SECONDS.sleep(time);
            System.out.println("当前线程："+Thread.currentThread().getName()+" 持有任务："+name+"，执行完成");
        }
    }
}
