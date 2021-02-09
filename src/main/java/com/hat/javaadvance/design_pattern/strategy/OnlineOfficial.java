package com.hat.javaadvance.design_pattern.strategy;

/**
 * 具体策略，某种行为的具体实现
 * 这里实现销售渠道抽象类，该具体渠道是在线上官网销售
 */
public class OnlineOfficial implements SaleChannel {
    @Override
    public void channel() {
        System.out.println("线上官网销售华为手机~~");
    }
}
