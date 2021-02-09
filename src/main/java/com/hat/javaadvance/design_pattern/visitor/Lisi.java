package com.hat.javaadvance.design_pattern.visitor;

/**
 * 具体访问者，李四，实现visit()方法，参数是某一步电影，然后操作FilmA、B对象的数据结构
 */
public class Lisi implements Person{
    @Override
    public void visit(FilmA filmA) {
        filmA.setScore(70);
        System.out.println("李四  给  FilmA  打了  "+70+"  分");
    }

    @Override
    public void visit(FilmB filmB) {
        filmB.setScore(88);
        System.out.println("李四  给  FilmB  打了  "+88+"  分");
    }
}
