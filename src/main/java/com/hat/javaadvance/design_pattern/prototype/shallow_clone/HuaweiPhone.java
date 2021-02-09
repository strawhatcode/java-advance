package com.hat.javaadvance.design_pattern.prototype.shallow_clone;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HuaweiPhone implements Cloneable {
    private String name;
    private int price;
    private int sales;
    private Parameter parameter;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
