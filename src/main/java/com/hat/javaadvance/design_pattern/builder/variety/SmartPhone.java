package com.hat.javaadvance.design_pattern.builder.variety;

import lombok.ToString;

@ToString
public class SmartPhone {
    private String cpu;
    private String ram;
    private String rom;
    private String screen;
    private String camera;
    // 可选
    private String nfc;
    private String infrared;
    private String Fingerprint;

    // 私有构造
    private SmartPhone(builder build) {
        this.cpu = build.cpu;
        this.ram = build.ram;
        this.rom = build.rom;
        this.screen = build.screen;
        this.camera = build.camera;
        this.nfc = build.nfc;
        this.infrared = build.infrared;
        this.Fingerprint = build.Fingerprint;
    }

    // 静态内部类，实现对SmartPhone对象的组装创建
    public static class builder{
        private String cpu;
        private String ram;
        private String rom;
        private String screen;
        private String camera;

        private String nfc;
        private String infrared;
        private String Fingerprint;

        public builder cpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public builder ram(String ram) {
            this.ram = ram;
            return this;
        }

        public builder rom(String rom) {
            this.rom = rom;
            return this;
        }

        public builder screen(String screen) {
            this.screen = screen;
            return this;
        }

        public builder camera(String camera) {
            this.camera = camera;
            return this;
        }

        public builder nfc(String nfc) {
            this.nfc = nfc;
            return this;
        }

        public builder infrared(String infrared) {
            this.infrared = infrared;
            return this;
        }

        public builder fingerprint(String fingerprint) {
            Fingerprint = fingerprint;
            return this;
        }

        public SmartPhone build(){
            return new SmartPhone(this);
        }
    }
}
