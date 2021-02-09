package com.hat.javaadvance.design_pattern.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象结构，电影的分数，有一个集合属性保存所有电影，
 * 一个往集合添加电影的方法add()和一个从集合删除电影的方法remove()
 * 还有一个展示访问者操作电影分数的方法showScore(),该方法传入一个访问者对象Person。
 */
public class FilmScore {
    private List<Film> films = new ArrayList<>();

    public void add(Film film){
        films.add(film);
    }

    public void remove(Film film){
        films.remove(film);
    }

    public void showScore(Person person){
        // 遍历保存的所有电影对象，并且给每个电影对象设置一个访问者对象
        films.forEach(f -> {
            f.accept(person);
        });
    }
}
