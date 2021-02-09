package com.hat.javaadvance.design_pattern.builder.classic;

/**
 * 抽象手机建造器
 */
public abstract class PhoneBuilder {
    public abstract void buildName();
    public abstract void buildCpu();
    public abstract void buildRam();
    public abstract void buildRom();
    public abstract void buildCamera();

    public abstract void buildNfc();
    public abstract void buildInfrared();
    public abstract void buildFingerprint();

    // 获取组装完成后的手机对象
    public abstract Phone getPhone();

}
