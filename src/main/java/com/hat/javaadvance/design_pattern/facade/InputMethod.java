package com.hat.javaadvance.design_pattern.facade;

/**
 * 子系统之一，输入法
 */
public class InputMethod {
    public void open(){
        System.out.println("启动输入法。。");
    }

    public void close(){
        System.out.println("关闭输入法。。");
    }
}
