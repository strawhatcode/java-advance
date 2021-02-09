package com.hat.javaadvance.thread;

import java.util.concurrent.*;

/**
 * 创建线程的4种方式
 */
public class ThreadCreate {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        wayOne();
        wayTwo();
        wayThree();
        wayFour();
        wayFive(); // 与1是一样的
    }

    // 1、通过new Thread()创建一个线程，调用start()方法启动线程
    private static void wayFive(){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("通过new Thread()创建一个线程");
            }
        });
        thread.start(); // 启动线程
        thread.interrupt();
    }

    // 1、继承Thread来创建一个线程
    private static void wayOne(){
        myThread myThread = new myThread();
        myThread.start(); // 启动线程
    }

    static class myThread extends Thread{
        @Override
        public void run() {
            System.out.println("通过继承Thread创建一个线程");
        }
    }

    // 2、实现runnable接口重写run()方法创建线程
    private static void wayTwo(){
        Thread thread = new Thread(new myRunnable());
        thread.start();
    }
    static class myRunnable implements Runnable{

        @Override
        public void run() {
            System.out.println("通过实现Runnable接口创建一个线程");
        }
    }

    // 3、实现callable接口实现call()接口，借助FutureTask来创建线程
    private static void wayThree() throws ExecutionException, InterruptedException {
        FutureTask<String> task = new FutureTask<>(new myCallable());
        new Thread(task).start(); // 启动线程
        String s = task.get();  // call()方法的返回值
        System.out.println(s);
    }
    static class myCallable implements Callable<String>{

        @Override
        public String call() throws Exception {
            System.out.println("使用Callable创建线程");
            return "callable返回值";
        }
    }
    // 4、通过线程池来创建线程
    private static void wayFour(){
        // 创建一个线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5,10,
                30, TimeUnit.SECONDS,new LinkedBlockingQueue<>(10),
                Executors.defaultThreadFactory(),new ThreadPoolExecutor.AbortPolicy());
        executor.submit(new myCallable());  // 通过submit()添加线程任务可以是callable或者是runnable，并且有返回值
        executor.execute(new myRunnable()); // 通过execute()添加线程任务只能是runnable，没有返回值
    }
}
