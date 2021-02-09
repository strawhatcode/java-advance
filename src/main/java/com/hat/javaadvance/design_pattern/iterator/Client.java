package com.hat.javaadvance.design_pattern.iterator;

import java.util.ArrayList;

/**
 * 迭代器模式：
 *  通过一个迭代器对象把聚合对象中的一些列对象按照顺序遍历出来，从而使聚合对象不用把自己的内部暴露出来，
 *  分离了聚合对象与其的遍历方法
 * 优点：
 *      - 不用暴露聚合对象的内部表示
 *      - 符合开闭原则和单一职责原则
 *      - 可以自定义迭代器来实现遍历的方式
 * 缺点：
 *      - 类增多，增加程序的复杂性
 * 具体场景：
 *      - Java的聚合（List、Map等）
 */
public class Client {
    public static void main(String[] args) {
        // 创建一个列表
        ArrayList<Object> list = new ArrayList<>();
        list.add("picA");
        list.add("picE");
        list.add("picF");
        // 创建一个图片对象，初始化图片对象的列表
        Picture picture = new MyPicture(list);
        // 图片对象新加两条数据
        picture.add("picC");
        picture.add("picB");
        // 获取图片对象的迭代器
        Iterator iterator = picture.iterator();
        // 遍历迭代器
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        System.out.println("----------------------");
        // 获取第一项数据
        System.out.println(iterator.first());
    }
}
