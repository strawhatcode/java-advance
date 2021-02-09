package com.hat.javaadvance.design_pattern.memento;

/**
 * 备忘录，存储发起人的状态信息
 */
public class Memento {
    private String tags;

    public Memento(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
}
