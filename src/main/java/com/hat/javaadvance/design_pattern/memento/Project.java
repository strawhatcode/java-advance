package com.hat.javaadvance.design_pattern.memento;

/**
 * 发起人，项目，有一个tag属性代表当前版本，
 * 提供一个创建备忘录的方法createMemento()和一个恢复恢复备忘录的方法restoreMemento()
 */
public class Project {
    private String tag;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Memento createMemento(){
        return new Memento(this.tag);
    }

    public void restoreMemento(Memento memento){
        this.tag = memento.getTags();
    }
}
