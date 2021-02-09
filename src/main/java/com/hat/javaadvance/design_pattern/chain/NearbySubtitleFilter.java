package com.hat.javaadvance.design_pattern.chain;

/**
 * 具体处理者
 */
public class NearbySubtitleFilter extends DanmuFilter{
    @Override
    public void doFilter(Danmu danmu) {
        if (danmu.isNearbySubtitle()){
            System.out.println(danmu.getValue() + "  已被NearbySubtitleFilter过滤");
        }else {
            System.out.println(danmu.getValue() + "  没有被NearbySubtitleFilter过滤");
            if (null != getNext()){
                getNext().doFilter(danmu);
            }else {
                System.out.println(danmu.getValue() + "未被过滤");
            }
        }
    }
}
