package com.hat.javaadvance.design_pattern.strategy;

/**
 * 环境角色类，客户端使用该类来进行华为手机的销售
 */
public class HuaweiPhoneSeller {
    private SaleChannel channel;

    public HuaweiPhoneSeller() {
    }

    public HuaweiPhoneSeller(SaleChannel channel) {
        this.channel = channel;
    }

    public SaleChannel getChannel() {
        return channel;
    }

    public void setChannel(SaleChannel channel) {
        this.channel = channel;
    }

    public void sell(){
        channel.channel();
    };
}
