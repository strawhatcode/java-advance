package com.hat.javaadvance.design_pattern.state;

/**
 * 具体状态，线程阻塞状态
 */
public class BlockState extends ThreadState {

    public BlockState(MyThread thread) {
        super(thread);
    }

    @Override
    public void behavior() {
        System.out.println(super.getThread().getName() + "线程进入阻塞状态~~~");
    }
}
