package com.hat.javaadvance.design_pattern.composite.safe;


/**
 * 叶子节点，树形结构的最深度，没有子节点
 * 代表一个文件
 */
public class FileLeaf implements IFile {
    private String name;

    public FileLeaf(String name) {
        this.name = name;
    }

    @Override
    public void show(String preifx) {
        System.out.print(preifx+ "-");
        System.out.println(name);
    }
}
