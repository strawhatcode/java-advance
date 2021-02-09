package com.hat.javaadvance.design_pattern.observer;

/**
 * 观察者模式：
 *  当一个被观察的对象改变时，观察它的一组对象都会收到相应的通知，即被观察对象与观察对象存在一对多的关系。
 *  也叫发布/订阅（Publish/Subscribe）模式
 *  优点：
 *      - 观察对象与被观察对象之间是抽象耦合关系，所以对象耦合性会降低
 *      - 观察对象与被观察对象之间建立了一套触发机制，即被观察对象有变化时，会触发某种机制通知观察对象
 *  缺点：
 *      - 当观察对象很多时， 通知所有观察对象时花费的时间也会很长
 *      - 观察对象与被观察对象之间可能会造成循环依赖
 *  具体场景：
 *      - 消息队列的发布/订阅机制
 */
public class Client {
    public static void main(String[] args) {
        UpAAA aaa = new UpAAA(); // 创建一个up主AAA
        aaa.addFan(new FanA());  // up主AAA增加一个粉丝A
        aaa.addFan(new FanB());  // up主AAA增加一个粉丝B
        aaa.publish("新视频~~"); // up主AAA发布一个视频
    }
}
