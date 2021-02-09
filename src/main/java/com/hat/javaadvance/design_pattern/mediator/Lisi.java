package com.hat.javaadvance.design_pattern.mediator;

/**
 * 具体同事类，李四
 */
public class Lisi extends User {
    public Lisi(String number) {
        super(number,"李四");
    }

    @Override
    public void callTo(User to) {
        System.out.println("李四打电话给 "+to.getName());
        operator.relay(this,to);
    }

    @Override
    public void fromCall(User from) {
        System.out.println("李四收到 "+from.getName()+" 的来电 ");
    }
}
