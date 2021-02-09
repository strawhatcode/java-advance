package com.hat.javaadvance.design_pattern.bridge;

/**
 * 桥接模式：使抽象与实现分离，使二者可以独立变化，使用合成/聚合代替继承
 * 使系统耦合度降低，类的数量减少。
 */
public class Main {
    public static void main(String[] args) {
        PhoneParameter p40p = PhoneParameter.builder()
                .cpu("麒麟990")
                .ram("8GB")
                .rom("256GB")
                .color("White")
                .build();
        Phone phone = new BuyPhone(new HuaweiP40P(),p40p);
        phone.getPhone();

        PhoneParameter mi10 = PhoneParameter.builder()
                .cpu("骁龙865")
                .ram("8GB")
                .rom("128GB")
                .color("Black")
                .build();
        Phone phone2 = new BuyPhone(new Xiaomi10(),mi10);
        phone2.getPhone();

    }
}
