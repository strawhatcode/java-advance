package com.hat.javaadvance.design_pattern.adapter.class_adapter;

/**
 * 类结构型模式：
 *  使用继承来实现，符合开闭原则，可以将原本接口不兼容的类一起工作
 */
public class ClassAdapterMain {
    public static void main(String[] args) {
        InputAdapter adapter = new InputAdapter();
        adapter.inputInterface();
    }
}
