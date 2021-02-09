package com.hat.javaadvance.design_pattern.mediator;

/**
 * 具体同事类，张三
 */
public class Zhangsan extends User {
    public Zhangsan(String number) {
        super(number,"张三");
    }

    @Override
    public void callTo(User to) {
        System.out.println("张三打电话给 "+to.getName());
        operator.relay(this,to);
    }

    @Override
    public void fromCall(User from) {
        System.out.println("张三收到 "+from.getName()+" 的来电 ");
    }
}
