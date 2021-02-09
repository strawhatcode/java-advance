package com.hat.javaadvance.design_pattern.chain;

/**
 * 具体处理者
 */
public class ColorFilter extends DanmuFilter {
    @Override
    public void doFilter(Danmu danmu) {
        if (!"white".equals(danmu.getColor())){
            System.out.println(danmu.getValue() + "  已被ColorFilter过滤");
        }else {
            System.out.println(danmu.getValue() + "  没有被ColorFilter过滤");
            if (null != getNext()){
                getNext().doFilter(danmu);
            }else {
                System.out.println(danmu.getValue() + "未被过滤");
            }
        }
    }
}
