package com.hat.javaadvance.design_pattern.command;

/**
 * 接收者，台灯
 */
public class Lamp extends Appliance{

    @Override
    public void onOrOff(String status) {
        if ("on".equals(status)){
            System.out.println("打开台灯~~~");
        }else {
            System.out.println("关闭台灯~~~");
        }
    }
}
