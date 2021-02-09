package com.hat.javaadvance.design_pattern.visitor;

/**
 * 抽象元素，电影，有一个抽象方法accept(),接受一个访问者作为参数
 */
public abstract class Film {
    protected String name;
    private int score;

    public Film(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public abstract void accept(Person person);
}
