package com.hat.javaadvance.design_pattern.flyweight;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 非享元对象,其中的属性是可变的
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DiffForSoftware {
    private String url;
}
