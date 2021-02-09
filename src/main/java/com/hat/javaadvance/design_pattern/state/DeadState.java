package com.hat.javaadvance.design_pattern.state;

/**
 * 具体状态，线程销毁状态
 */
public class DeadState extends ThreadState {
    public DeadState(MyThread thread) {
        super(thread);
    }

    @Override
    public void behavior() {
        System.out.println(super.getThread().getName()+"线程已销毁~~~");
    }
}
