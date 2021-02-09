package com.hat.javaadvance.design_pattern.adapter.class_adapter;

/**
 * 适配器（adapter），接口转换器，使用转换器将3.5mm的耳机转成typec接口
 */
public class InputAdapter extends Earphone implements NewPhone{
    // 实现目标接口
    @Override
    public String inputInterface() {
        // 调用适配者本来就有的方法
        String s = this.earphoneInput();
        // 将适配者的的3.5mm转换成typeC
        if ("3.5mm".equals(s)){
            System.out.println("转换成typcC接口");
            return "typeC";
        }
        System.out.println("不支持 "+s+" 接口的转换");
        return s;
    }
}
