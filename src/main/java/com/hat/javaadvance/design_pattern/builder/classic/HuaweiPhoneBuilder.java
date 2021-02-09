package com.hat.javaadvance.design_pattern.builder.classic;

/**
 * 具体手机建造器
 */
public class HuaweiPhoneBuilder extends PhoneBuilder {
    private Phone phone;

    public HuaweiPhoneBuilder() {
        this.phone = new Phone();
    }

    @Override
    public void buildName() {
        phone.setName("华为P50");
    }

    @Override
    public void buildCpu() {
        phone.setCpu("麒麟1080");
    }

    @Override
    public void buildRam() {
        phone.setRam("12GB");
    }

    @Override
    public void buildRom() {
        phone.setRom("512GB");
    }

    @Override
    public void buildCamera() {
        phone.setCamera("1亿像素");
    }

    @Override
    public void buildNfc() {
        phone.setNfc("有");
    }

    @Override
    public void buildInfrared() {
        phone.setInfrared("有");
    }

    @Override
    public void buildFingerprint() {
        phone.setFingerprint("有");
    }

    @Override
    public Phone getPhone() {
        return phone;
    }
}
