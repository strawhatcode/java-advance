package com.hat.javaadvance.design_pattern.prototype.deep_clone;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ParameterXiaomi implements Serializable{
    private String cpu;
    private String ram;
    private String rom;
    private String cameraPixels;

}
