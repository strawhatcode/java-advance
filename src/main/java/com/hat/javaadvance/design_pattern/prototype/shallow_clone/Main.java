package com.hat.javaadvance.design_pattern.prototype.shallow_clone;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        HuaweiPhone phone = new HuaweiPhone("P40P+",6999,123456,
                new Parameter("麒麟990","12GB","512GB","6400万"));
        System.out.println("浅克隆前："+phone);
        System.out.println("浅克隆前hashcode："+phone.hashCode());
        System.out.println("浅克隆前parameter的hashcode："+phone.getParameter().hashCode());
        HuaweiPhone clone = (HuaweiPhone) phone.clone();
        System.out.println("浅克隆后："+clone);
        System.out.println("浅克隆后hashcode："+clone.hashCode());
        System.out.println("浅克隆后parameter的hashcode："+clone.getParameter().hashCode());
        phone.getParameter().setCameraPixels("4800万");
        System.out.println("修改克隆前的引用属性cameraPixels克隆后的："+clone.getParameter().getCameraPixels());
    }
}
