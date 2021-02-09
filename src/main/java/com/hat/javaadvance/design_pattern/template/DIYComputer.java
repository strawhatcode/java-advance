package com.hat.javaadvance.design_pattern.template;

public class DIYComputer extends AbstractComputer {
    @Override
    public void motherboard() {
        System.out.println("适用3700x针脚的主板");
    }

    @Override
    public void cpu() {
        System.out.println("AMD Ryzen 3700x");
    }

    @Override
    public void gpu() {
        System.out.println("RTX 3070");
    }
    // ssd hdd都要
    @Override
    public String hddOrSsd() {
        return "all";
    }

    // 使用显卡
    @Override
    public boolean useGpu() {
        return true;
    }

    // 不使用cpu自带风扇
    @Override
    public boolean cpuWithFan() {
        return false;
    }
}
