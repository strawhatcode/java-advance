package com.hat.javaadvance.design_pattern.visitor;

/**
 * 具体访问者，张三，实现visit()方法，参数是某一步电影，然后操作FilmA、B对象的数据结构
 */
public class Zhangsan implements Person {
    @Override
    public void visit(FilmA filmA) {
        filmA.setScore(90);
        System.out.println("张三  给  FilmA  打了  "+90+"  分");
    }

    @Override
    public void visit(FilmB filmB) {
        filmB.setScore(87);
        System.out.println("张三  给  FilmB  打了  "+87+"  分");
    }
}
