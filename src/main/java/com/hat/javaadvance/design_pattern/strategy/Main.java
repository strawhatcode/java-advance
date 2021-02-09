package com.hat.javaadvance.design_pattern.strategy;

/**
 * 策略模式：把某种行为抽象出来，然后根据不同的策略来实现这种行为。
 * 该模式可以一定程度的代替if...else...
 * 当默认实现的行为不满足需求时，只需要再创建一种策略来继承抽象策略接口。同时也符合开闭原则
 * 具体场景：
 *      Arrays.sort()数组的排序函数
 */
public class Main {
    public static void main(String[] args) {
        HuaweiPhoneSeller seller = new HuaweiPhoneSeller();
        // 线下
        seller.setChannel(new Offline());
        seller.sell();

        //线上官网
        seller.setChannel(new OnlineOfficial());
        seller.sell();

        //自定义一个销售渠道（策略），使用lambda表达式创建一种策略
        seller.setChannel(() -> System.out.println("新增一个销售渠道~~~~~"));
        seller.sell();
    }
}
