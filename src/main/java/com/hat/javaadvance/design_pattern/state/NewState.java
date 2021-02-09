package com.hat.javaadvance.design_pattern.state;

/**
 * 具体状态，线程新建状态，实现新建状态的行为
 */
public class NewState extends ThreadState{

    public NewState(MyThread thread) {
        super(thread);
    }

    @Override
    public void behavior() {
        System.out.println(super.getThread().getName()+" 线程创建~~");
    }
}
