package com.hat.javaadvance.design_pattern.builder.classic;

/**
 * 指导者
 */
public class Director {
    private PhoneBuilder phoneBuilder;

    public Director(PhoneBuilder phoneBuilder) {
        this.phoneBuilder = phoneBuilder;
    }

    public Phone buildPhone(){
        phoneBuilder.buildCamera();
        phoneBuilder.buildCpu();
        phoneBuilder.buildFingerprint();
        phoneBuilder.buildInfrared();
        phoneBuilder.buildName();
        phoneBuilder.buildNfc();
        phoneBuilder.buildRam();
        phoneBuilder.buildRom();
        return phoneBuilder.getPhone();
    }
}
