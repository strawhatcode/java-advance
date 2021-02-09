package com.hat.javaadvance.thread.threefeature;

import java.util.concurrent.TimeUnit;

/**
 * 并发编程线程的三大特性----可见性
 */
public class Visibility {
    // 加和不加volatile关键字的区别
    // 没加volatile： 程序不会退出
    //      主线程修改的flag不会被Thread线程得到，因为主线程在修改flag之前，Thread线程就已经得到flag的副本了，
    //      因此while (!flag)永远成立
    // 加volatile：   程序会退出
    //      加了volatile关键字后，主线程修改了flag并刷新到主存，这时Thread线程里的flag变量副本会无效，
    //      所以它需要去主存中重新获取flag的值，新的flag副本的值已经是true，所以while (!flag)不成立
    private static volatile boolean flag = false;
    public static void main(String[] args) throws InterruptedException {
        // 启动一个线程判断flag，这时该线程已经从主存中拿到flag = false，而且只读这个变量，并没有修改
        new Thread(() -> {
            System.out.println("程序开始运行~~~~");
            while (!flag){
            }
            System.out.println("~~~程序结束运行");
        }).start();
        // 主线程休眠1秒等上面的线程先启动运行
        TimeUnit.SECONDS.sleep(1L);
        flag = true;  // 这时主线程修改flag的值，修改完后会刷新到主存
    }
}
