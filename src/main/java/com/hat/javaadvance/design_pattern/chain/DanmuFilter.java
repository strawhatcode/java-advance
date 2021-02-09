package com.hat.javaadvance.design_pattern.chain;

/**
 * 抽象处理者
 */
public abstract class DanmuFilter {
    // 当前过滤器有下一个过滤器的引用
    private DanmuFilter next;

    public DanmuFilter getNext() {
        return next;
    }

    public void setNext(DanmuFilter next) {
        this.next = next;
    }
    // 具体处理逻辑，让具体过滤器来实现
    public abstract void doFilter(Danmu danmu);
}
