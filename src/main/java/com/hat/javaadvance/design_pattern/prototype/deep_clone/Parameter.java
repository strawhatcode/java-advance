package com.hat.javaadvance.design_pattern.prototype.deep_clone;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parameter implements Cloneable{
    private String cpu;
    private String ram;
    private String rom;
    private String cameraPixels;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
