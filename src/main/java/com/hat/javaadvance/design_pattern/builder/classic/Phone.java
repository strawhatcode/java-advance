package com.hat.javaadvance.design_pattern.builder.classic;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Phone产品通用的参数
 */
@Setter
@Getter
@ToString
public class Phone {
    private String name;
    private String cpu;
    private String ram;
    private String rom;
    private String camera;

    private String nfc;
    private String infrared;
    private String Fingerprint;

}
