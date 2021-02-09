package com.hat.javaadvance.design_pattern.template;

/**
 * 模版方法模式
 * 自定义一套模版，然后具体业务根据这套模版来实现具体需求
 */
public class Main {
    public static void main(String[] args) {
        DIYComputer diyComputer = new DIYComputer();
        diyComputer.assemble();
    }
}
