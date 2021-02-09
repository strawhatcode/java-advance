package com.hat.javaadvance.design_pattern.facade;

/**
 * 子系统之一，微信
 */
public class Wechat {
    public void open(){
        System.out.println("启动微信。。");
    }

    public void close(){
        System.out.println("关闭微信。。");
    }
}
