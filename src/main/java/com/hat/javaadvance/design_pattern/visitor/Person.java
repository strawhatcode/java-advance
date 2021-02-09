package com.hat.javaadvance.design_pattern.visitor;

/**
 * 访问者，两个方法分别对应两个元素（电影），参数分别是两部电影
 */
public interface Person {
    void visit(FilmA filmA);
    void visit(FilmB filmB);
}
