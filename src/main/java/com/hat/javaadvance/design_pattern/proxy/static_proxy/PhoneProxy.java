package com.hat.javaadvance.design_pattern.proxy.static_proxy;

/**
 * 代理对象，提供与真实主题一样的方法，并且可以对真实主题的方法调用前后做一些其他的处理
 */
public class PhoneProxy implements PhoneSales{
    private PhoneSales sales;

    public PhoneProxy() {
    }

    public PhoneProxy(PhoneSales sales) {
        this.sales = sales;
    }

    @Override
    public void sales() {
        // 默认是通过线下实体店来销售手机
        if (null == sales){
            sales = new OfflinePhoneSales();
        }
        beforeSales();
        sales.sales();
        afterSales();
    }
    // 两个增强方法
    private void beforeSales(){
        System.out.println("售出手机之前。砍价或者送耳机等等");
    }
    private void afterSales(){
        System.out.println("售出手机之后。赠送xxx年售后服务");
    }
}
