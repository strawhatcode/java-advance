package com.hat.javaadvance.thread;


import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) throws InterruptedException {
        ThreadState state = new ThreadState();
        state.state();
//        state.state2();
    }

    private void state2() throws InterruptedException {
        Thread thread = new Thread(() -> {
            test();
        }); // 开启一个thread线程调用同步方法 NEW状态
        thread.start();  // 启动thread线程 RUNNABLE状态
        Thread.sleep(500);   // 主线程睡眠500ms等待thread线程进入test()同步方法
        System.out.println("线程："+thread.getName()+" A状态：" + thread.getState()); // 对应test()方法的1. WAITING状态

        // Object.wait()与Object.notify()/Object.notifyAll()必须在同步代码块里面调用，
        // 否则会抛出java.lang.IllegalMonitorStateException异常
        synchronized (this) {
            this.notifyAll();  // 唤醒thread线程
            System.out.println("线程："+thread.getName()+" B状态：" + thread.getState()); // 对应test()方法的1. BLOCKED状态
        }

        // 开启第二个线程来模拟join()
        Thread thread1 = new Thread(() -> {
            try {
                thread.join(); // thread1线程等待thread执行完毕后再继续执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread1.start();  // 启动thread1线程
        TimeUnit.MILLISECONDS.sleep(500L); // 休眠500毫秒等待thread1执行线程代码
        // thread1线程中使用了join()方法后会进入WAITING状态
        System.out.println("线程："+thread1.getName()+" A状态：" + thread1.getState());

        // thread已经被唤醒，在执行代码
        System.out.println("线程："+thread.getName()+" C状态：" + thread.getState()); // 对应test()方法的2. TIMED_WAITING状态

        thread1.join();  // 等待thread1执行完成，模拟thread执行完成
        System.out.println("线程："+thread.getName()+" D状态：" + thread.getState()); // 对应test()方法的3. TERMINATED状态
    }

    // 模拟同步代码方法
    private synchronized void test() {
        System.out.println("进入同步方法~~~");
        try {
            // 1.
            this.wait();  // 当前对象放弃cpu时间片进入WAITING状态，等待被notify唤醒，BLOCKED状态（A、B状态）
            System.out.println("线程被唤醒~~~");
            // 2.
            Thread.sleep(3000);  // 调用sleep(long)后当前线程会进入 TIMED_WAITING状态（C状态）
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3.
        System.out.println("同步方法执行完毕~~"); // 执行完毕后进入TERMINATED状态 （D状态）
    }

    // 线程的NEW与RUNNABLE与TERMINATED状态
    private void state() {
        Thread thread = new Thread(() -> {
            // 开始执行线程种的代码 RUNNABLE状态
            System.out.println("A状态：" + Thread.currentThread().getState());
            System.out.println("进入到线程:" + Thread.currentThread().getName());
        }); // 创建一个线程 NEW状态
        System.out.println("B状态：" + thread.getState());
        thread.start();  // 启动一个线程 RUNNABLE状态
        System.out.println("C状态：" + thread.getState());
        try {
            Thread.sleep(3000);  // 主线程等待3秒等带thread线程执行完毕
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 3秒后thread已经执行完毕 TERMINATED状态
        System.out.println("D状态：" + thread.getState());
    }
}
