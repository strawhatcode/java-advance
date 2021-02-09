package com.hat.javaadvance.design_pattern.composite.transparent;

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
    public void add(IFile file) {
        throw new RuntimeException("叶子节点不支持add");
    }

    @Override
    public void remove(IFile file) {
        throw new RuntimeException("叶子节点不支持remove");
    }

    @Override
    public IFile getChild(int i) {
        throw new RuntimeException("叶子节点不支持getChild");
    }

    @Override
    public void show(String preifx) {
        System.out.print(preifx+ "-");
        System.out.println(name);
    }
}
