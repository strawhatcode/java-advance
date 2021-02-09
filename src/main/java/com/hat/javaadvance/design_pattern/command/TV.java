package com.hat.javaadvance.design_pattern.command;

/**
 * 接收者，电视
 */
public class TV extends Appliance{

    @Override
    public void onOrOff(String status) {
        if ("on".equals(status)){
            System.out.println("打开电视~~~");
        }else {
            System.out.println("关闭电视~~~");
        }
    }
}
