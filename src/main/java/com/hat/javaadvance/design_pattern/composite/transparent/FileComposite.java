package com.hat.javaadvance.design_pattern.composite.transparent;

import java.util.ArrayList;
import java.util.List;

/**
 * 树枝节点，负责管理和添加叶子节点
 * 代表文件夹，可以新增、删除、获取文件夹里的文件
 */
public class FileComposite implements IFile {
    private List<IFile> files = new ArrayList<>();
    private String name;

    public FileComposite(String name) {
        this.name = name;
    }

    @Override
    public void add(IFile file) {
        files.add(file);
    }

    @Override
    public void remove(IFile file) {
        files.remove(file);
    }

    @Override
    public IFile getChild(int i) {
        return files.get(i);
    }

    @Override
    public void show(String preifx) {
        System.out.println(preifx+ "+"+name);
        preifx += "  ";
        for (IFile file : files) {
            file.show(preifx);
        }
    }
}
