package com.hat.javaadvance.design_pattern.composite.safe;

/**
 * 抽象构件，文件的抽象表示
 */
public interface IFile {

    void show(String preifx); // 参数只是辅助输出的结构，应根据具体需求指定相应的参数
}
