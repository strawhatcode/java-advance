package com.hat.javaadvance.design_pattern.visitor;

/**
 * 具体元素，电影A，实现抽象方法accept()。实现的内容是根据把当前对象作为参数传给访问者的visit()方法
 */
public class FilmA extends Film {
    public FilmA() {
        super("FilmA");
    }

    @Override
    public void accept(Person person) {
        person.visit(this);
    }

}
