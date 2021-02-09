package com.hat.javaadvance.design_pattern.facade;

/**
 * 子系统之一，电话服务
 */
public class PhoneService {
    public void open(){
        System.out.println("启动电话服务。。");
    }

    public void close(){
        System.out.println("关闭电话服务。。");
    }
}
