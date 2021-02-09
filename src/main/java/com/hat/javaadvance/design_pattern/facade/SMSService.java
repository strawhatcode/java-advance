package com.hat.javaadvance.design_pattern.facade;

/**
 * 子系统之一，短信服务
 */
public class SMSService {
    public void open(){
        System.out.println("启动短信服务。。");
    }

    public void close(){
        System.out.println("关闭短信服务。。");
    }
}
