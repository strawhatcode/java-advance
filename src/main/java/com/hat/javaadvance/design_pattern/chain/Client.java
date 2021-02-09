package com.hat.javaadvance.design_pattern.chain;

/**
 * 责任链模式：
 *  一个请求沿着一条对象链寻找可以处理这个请求的对象，
 *  直到能找到处理对象，也有可能这条对象链没有能处理这个请求的对象，
 *  上一个对象有下一个对象的引用，或者把这些处理对象封装进一个列表中，然后按顺序来执行这些处理对象
 *  优点：
 *      - 请求者与处理者分离，请求者无需知道这条对象链的结构，也无需知道处理者在链中的位置，降低了对象间的耦合度
 *      - 容易扩展，如果有新的处理对象，只要新增一个，符合开闭原则
 *      - 可以一定程度代替if...else...
 *      - 每个处理对象相互独立，符合单一职责原则
 *  缺点：
 *      - 客户端需要建立一条对象链，复杂度会增高
 *      - 不能保证请求一定被处理，有可能走完一条对象链都找不到合适的处理对象
 *  具体场景：
 *      - spring的filter
 *      - servlet的filter
 */
public class Client {
    public static void main(String[] args) {
        ColorFilter colorFilter = new ColorFilter();
        RollFilter rollFilter = new RollFilter();
        NearbySubtitleFilter nearbySubtitleFilter = new NearbySubtitleFilter();
        // 创建过滤链
        colorFilter.setNext(rollFilter);
        rollFilter.setNext(nearbySubtitleFilter);

        // 创建一条弹幕
        Danmu danmu = Danmu.builder()
                .color("white")
                .isRoll(true)
                .value("这是一条弹幕")
                .nearbySubtitle(true)
                .build();

        // 调用过滤器
        colorFilter.doFilter(danmu);
    }
}
