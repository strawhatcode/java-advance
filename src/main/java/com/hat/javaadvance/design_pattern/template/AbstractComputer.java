package com.hat.javaadvance.design_pattern.template;

public abstract class AbstractComputer {

    // 组装
    public void assemble(){
        System.out.println("组装电脑，以下硬件");
        motherboard(); // 主板
        cpu();   // cpu
        if (useGpu()){   // 判断钩子方法是否有显卡
            gpu();
        }
        cpuFan(cpuWithFan());  // 钩子方法判断使用cpu自带风扇还是另买风扇
        ram();  // 内存条
        power(); // 电源
        // 判断使用哪种硬盘还是两种硬盘都要
        if ("hdd".equals(hddOrSsd())){
            hdd();
        }else if ("ssd".equals(hddOrSsd())){
            ssd();
        }else {
            hdd();
            ssd();
        }

    }
    // 主板，不同平台主板也不一样，子类实现
    public abstract void motherboard();
    // cpu，AMD或者Intel，子类实现
    public abstract void cpu();
    // 显卡，AMD或者NVIDIA，子类实现
    public abstract void gpu();
    // cpu风扇，盒装cpu附带或者单买
    public void cpuFan(boolean fan){
        if (fan){
            System.out.println("cpu盒装风扇");
        }else {
            System.out.println("玄冰400风扇");
        }
    }
    // 内存条，默认普通内存条
    public void ram(){
        System.out.println("DDR4 3200MHz内存条");
    }
    // 电源，必须
    public void power(){
        System.out.println("750W额定功率电源");
    }
    // 机械硬盘，与ssd二选一或者都要
    public void hdd(){
        System.out.println("1TB机械硬盘");
    }
    // 固态硬盘，与hdd二选一或者都要
    public void ssd(){
        System.out.println("512GB固态硬盘");
    }

    // 钩子方法，使用hdd还使ssd还是两种都要
    public String hddOrSsd(){
        return "";
    }
    // 钩子方法，是否使用显卡
    public boolean useGpu(){
        return false;
    }
    // 钩子方法，是否使用cpu自带风扇
    public boolean cpuWithFan(){
        return true;
    }
}
