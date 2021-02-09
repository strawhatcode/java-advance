package com.hat.javaadvance.design_pattern.visitor;

/**
 * 访问者模式：
 *  在不改变对象的数据结构的情况下对对象继续一些额外的数据操作，即对象的数据结构不变，对象的数据操作可以改变，
 *  所以对象的数据结构与对象的数据操作是分开的
 *  优点：
 *      - 扩展性好，可以在不修改对象的结构的情况下增加对象的行为
 *      - 符合单一职责原则，每个类只负责自己的责任
 *      - 对象的数据结构与数据操作行为解耦，不同行为不会影响对象的结构’‘
 * 缺点：
 *      - 违背了开闭原则，如果对象新增一种数据结构（元素），则需要修改很多类
 *      - 违背了依赖倒置原则，访问者中依赖了具体的类，而不是接口或抽象类
 *      - 违背了迪米特法则，具体元素对访问者公布细节，这破坏了对象的封装性
 *
 */
public class Client {
    public static void main(String[] args) {
        // 创建一个访问者张三
        Zhangsan zhangsan = new Zhangsan();
        // 创建一个访问者李四
        Lisi lisi = new Lisi();
        // 创建一个对象结构 电影分数
        FilmScore filmScore = new FilmScore();
        // 往对象结构中添加两部电影
        filmScore.add(new FilmA());
        filmScore.add(new FilmB());
        // 展示张三对两部电影的评分
        filmScore.showScore(zhangsan);
        System.out.println("--------------");
        // 展示李四对两部电影的评分
        filmScore.showScore(lisi);
    }
}
