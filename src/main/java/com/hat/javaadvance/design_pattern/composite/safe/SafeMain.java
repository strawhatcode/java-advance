package com.hat.javaadvance.design_pattern.composite.safe;


/**
 * 组合模式----安全模式：
 * 安全模式中的抽象构件（Component）只需要声明叶子节点与树枝节点共同的方法，
 * 叶子节点（Leaf）实现抽象构件（Component）的方法，
 * 树枝节点（Composite）实现抽象构件（Component）的方法，并且增加add()、remove()、getChild()等管理叶子节点的方法
 */
public class SafeMain {
    public static void main(String[] args) {
        FileComposite f1 = new FileComposite("安全模式文件夹A");
        FileComposite f2 = new FileComposite("安全模式文件夹B");
        FileComposite f3 = new FileComposite("安全模式文件夹C");
        // 文件夹A里的文件
        f1.add(new FileLeaf("fileA1.txt"));
        f1.add(new FileLeaf("fileA2.java"));
        f1.add(new FileLeaf("fileA2.class"));
        f1.add(f2);

        // 文件夹B里的文件
        f2.add(new FileLeaf("文件B1.docx"));
        f2.add(new FileLeaf("文件B2.pptx"));
        f2.add(f3);

        // 文件夹C里的文件
        f3.add(new FileLeaf("fileCCC.ddl"));

        // 输出整个文件树
        f1.show(" ");
    }
}
