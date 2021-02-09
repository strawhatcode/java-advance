package com.hat.javaadvance.design_pattern.state;

/**
 * 抽象状态，持有环境上下文（MyThread对象）
 */
public abstract class ThreadState {
    private MyThread thread;

    public ThreadState(MyThread thread) {
        this.thread = thread;
    }

    public MyThread getThread() {
        return thread;
    }

    public void setThread(MyThread thread) {
        this.thread = thread;
    }

    public abstract void behavior();
}
