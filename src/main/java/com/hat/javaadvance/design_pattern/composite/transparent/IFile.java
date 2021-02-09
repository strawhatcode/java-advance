package com.hat.javaadvance.design_pattern.composite.transparent;

/**
 * 抽象构件，文件的抽象表示
 */
public interface IFile {
    void add(IFile file);
    void remove(IFile file);
    IFile getChild(int i);
    void show(String preifx); // 参数只是辅助输出的结构，应根据具体需求指定相应的参数
}
