package com.hat.javaadvance.design_pattern.strategy;

/**
 * 具体策略
 * 该具体销售渠道是线下实体店
 */
public class Offline implements SaleChannel {
    @Override
    public void channel() {
        System.out.println("线下实体店销售华为手机~~~~");
    }
}
