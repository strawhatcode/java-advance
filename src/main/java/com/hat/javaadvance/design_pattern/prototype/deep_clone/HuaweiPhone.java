package com.hat.javaadvance.design_pattern.prototype.deep_clone;

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

    /**
     * 对当前对象引用到的其他对象也进行克隆
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        HuaweiPhone o = (HuaweiPhone) super.clone();
        // Parameter对象不为空时进行Parameter对象的克隆
        if (o.getParameter() != null){
            o.parameter = (Parameter) parameter.clone();
        }
        return o;
    }
}
