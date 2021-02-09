package com.hat.javaadvance.design_pattern.prototype.shallow_clone;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Parameter {
    private String cpu;
    private String ram;
    private String rom;
    private String cameraPixels;
}
