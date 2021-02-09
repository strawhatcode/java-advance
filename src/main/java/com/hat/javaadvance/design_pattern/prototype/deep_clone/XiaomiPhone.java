package com.hat.javaadvance.design_pattern.prototype.deep_clone;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class XiaomiPhone implements Serializable{
    private String name;
    private int price;
    private int sales;
    private ParameterXiaomi parameter;

}
