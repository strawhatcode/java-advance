package com.hat.javaadvance.design_pattern.composite.transparent;

/**
 * 组合模式----透明模式：
 * 透明模式中的抽象构件（Component）要声明组合需要用到的所有方法，
 * 比如树枝节点（Composite）需要用到的add()、remove()、getChild()等方法，
 * 而叶子节点（Leaf）实现抽象构件（Component）时不用实现这些方法，直接做空处理或者直接抛出异常。
 */
public class TransparentMain {
    public static void main(String[] args) {
        IFile f1 = new FileComposite("文件夹A");
        IFile f2 = new FileComposite("文件夹B");
        IFile f3 = new FileComposite("文件夹C");
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
