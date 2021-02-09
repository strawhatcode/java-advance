package com.hat.javaadvance.design_pattern.chain;

/**
 * 具体处理者
 */
public class RollFilter extends DanmuFilter {
    @Override
    public void doFilter(Danmu danmu) {
        if (!danmu.isRoll()){
            System.out.println(danmu.getValue() + "  已被RollFilter过滤");
        }else {
            System.out.println(danmu.getValue() + "  没有被RollFilter过滤");
            if (null != getNext()){
                getNext().doFilter(danmu);
            }else {
                System.out.println(danmu.getValue() + "未被过滤");
            }
        }
    }
}
