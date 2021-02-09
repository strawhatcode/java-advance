package com.hat.javaadvance.design_pattern.state;

/**
 * 具体状态，线程就绪状态
 */
public class RunnableState extends ThreadState {

    public RunnableState(MyThread thread) {
        super(thread);
    }

    @Override
    public void behavior() {
        System.out.println(super.getThread().getName()+"线程进入运行状态~~");
    }
}
