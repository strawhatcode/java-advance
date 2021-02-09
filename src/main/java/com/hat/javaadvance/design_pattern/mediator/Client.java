package com.hat.javaadvance.design_pattern.mediator;

/**
 * 中介者模式
 * 两个对象之间的交互通过一个中介者来调度，A对象需要找到B对象做一些处理，A只需要把B对象的某种表示给中介者，
 * 然后中介者就会帮A对象找到B对象。
 * 优点：
 *      - 降低了对象之间的耦合性
 *      - 把一对多关系转为一对一关系（普通对象与中介者的一对一关系）
 * 缺点：
 *      - 中介者对象可能会变得很庞大很复杂
 * 具体场景：
 *      - SpringMVC，C（controller）是M（model）与V（view）的中介者
 */
public class Client {
    public static void main(String[] args) {
        Operators operator = new Telecom();  // 创建一个中介者
        User lisi = new Lisi("123");   // 创建李四的电话号码
        User zhangsan = new Zhangsan("789"); // 创建张三的电话号码

        operator.register(lisi);  // 运营商注册李四的电话号码
        operator.register(zhangsan); // 运营商注册张三的电话号码
        zhangsan.callTo(lisi);  // 张三打电话给李四
    }
}
