package com.hat.javaadvance.design_pattern.bridge;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 具体实现化角色（手机参数）
 */
@Setter
@Getter
@Builder
public class PhoneParameter {
    private String cpu;
    private String ram;
    private String rom;
    private String color;

    @Override
    public String toString() {
        return "参数如下：{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", rom='" + rom + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
