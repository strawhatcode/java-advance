package com.hat.javaadvance.design_pattern.state;

/**
 * 线程上下文对象（环境），里有一个状态对象，用来表示处于某种状态
 */
public class MyThread {
    private ThreadState state; // 线程状态
    private String name; // 线程名称

    // new一个线程时创建线程
    public MyThread(String name) {
        this.name = name;
        state = new NewState(this);
        state.behavior();
    }

    public ThreadState getState() {
        return state;
    }

    public void setState(ThreadState state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // 调用start时线程进入runnable状态
    public void start() {
        state = new RunnableState(this);
        state.behavior();
    }

    // 线程在就绪状态时获取cpu资源开始执行线程
    public void getCpuResource(){
        state = new RunningState(this);
        state.behavior();
        System.out.println("执行完毕~~");
    }

    // 线程睡眠，进入阻塞状态
    public void sleep(long ms) throws InterruptedException {
        state = new BlockState(this);
        Thread.sleep(ms);
        System.out.println("阻塞了 "+ms+" 毫秒");
        // 阻塞完成后再次进入runnable状态
        start();
    }

    // 线程执行完毕后销毁线程
    public void destroy(){
        state = new DeadState(this);
        state.behavior();
    }
}
