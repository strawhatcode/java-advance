package com.hat.javaadvance.design_pattern.facade;

/**
 * 子系统之一，网络服务
 */
public class NETService {
    public void open(){
        System.out.println("启动网络服务。。");
    }

    public void close(){
        System.out.println("关闭网络服务。。");
    }
}
