package com.hat.javaadvance.design_pattern.builder.variety;

public class Main {
    public static void main(String[] args) {
        SmartPhone phone = new SmartPhone.builder()
                .cpu("骁龙865")
                .camera("6400万像素")
                .fingerprint("屏幕指纹")
                .infrared("无红外线")
                .ram("8Gb运行内存")
                .rom("512Gb存储内存")
                .screen("OLED屏幕")
                .build();
        System.out.println(phone);
    }
}
