package com.hat.javaadvance.thread.threefeature;

/**
 * 并发编程线程的三大特性----有序性
 */
public class Order {
    private static volatile int a, b = 0;
    private static int x, y;
    private static long count = 0;

    private static int num = 1;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        Order order = new Order();
        order.orderTest();
    }
    private void orderTest() throws InterruptedException {
        while (true) {
            a = 0;
            b = 0;
            x = 0;
            y = 0;
            // 线程t1与t2按照代码顺序来说，1.先于2.执行，3.先于4.执行，
            // 但是由于1.和2.  3.和4.没有关联，编译器与CPU为了效率可能会对1.2.或者3.4.进行指令重排序，
            // 这就导致了结果可能是2.先于1.  4.先于3.执行，所以结果有可能x, y = 0
            // 解决：使用volatile关键字不仅可以保证变量的可见性，还可以防止指令重排序。
            Thread t1 = new Thread(() -> {
                a = 1;  // 1.
                x = b;  // 2.
            });
            Thread t2 = new Thread(() -> {
                b = 1;  // 3.
                y = a;  // 4.
            });
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            count++;
            if (y == 0 && x == 0) {
                System.out.println("x:" + x + ", y:" + y + "  ---  " + count);
                break;
            } else if ((y == 1 && x == 0) || (y == 0 && x == 1)) {
                System.out.println("x:" + x + ", y:" + y + "  ---  " + count);
            }
        }
    }

    private void orderTest2(){
        // 线程t1有步骤1和2，线程t2有步骤3和4。1和2没有关联，所以可能会发生重排序。
        // 程序在执行时可能会出现以下情况：
        //      1,2,3,4 / 2,1,3,4    flag:true, num：99
        //      1,3,4,2 / 1,3,2,4    flag:false, num：99 ,由于flag并没有保证可见性，所以flag还是false
        //      3,4,1,2              flag:false, num：1
        //      2,3,4,1              flag:true, num：1
        // 由于编译器或者CPU的重排序，导致会出现多种情况
        for (int i = 0; i < 10000; i++) {
            num = 1;
            flag = false;
            Thread t1 = new Thread(() -> {
                num = 99;  // 1.
                flag = true;  // 2.
            });
            Thread t2 = new Thread(() -> {
                if (flag){  // 3.
                    System.out.println("true:"+flag+", num："+num); // 4.
                }else {
                    System.out.println("false:"+flag+", num："+num);
                }
            });
            t1.start();
            t2.start();
        }
    }
}
