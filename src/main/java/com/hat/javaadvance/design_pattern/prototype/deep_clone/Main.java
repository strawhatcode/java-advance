package com.hat.javaadvance.design_pattern.prototype.deep_clone;


import java.io.*;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException, IOException, ClassNotFoundException {
//        testClone();
        testSerializable();
    }

    public static void testSerializable() throws IOException, ClassNotFoundException {
        XiaomiPhone phone = new XiaomiPhone("Mi10",5999,11122,
                new ParameterXiaomi("骁龙865","8GB","256GB","1亿"));
        System.out.println("深克隆前："+phone);
        System.out.println("深克隆前hashcode："+phone.hashCode());
        System.out.println("深克隆前parameter的hashcode："+phone.getParameter().hashCode());
        // 序列化
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(phone);
        // 反序列化
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        XiaomiPhone clone = (XiaomiPhone) ois.readObject();
        System.out.println("深克隆后："+clone);
        System.out.println("深克隆后hashcode："+clone.hashCode());
        System.out.println("深克隆后parameter的hashcode："+clone.getParameter().hashCode());
        phone.getParameter().setCameraPixels("6400万");
        System.out.println("修改克隆前的引用属性cameraPixels克隆后的："+clone.getParameter().getCameraPixels());
    }

    public static void testClone() throws CloneNotSupportedException {
        HuaweiPhone phone = new HuaweiPhone("P40P",5999,22222,
                new Parameter("麒麟990","8GB","256GB","6400万"));
        System.out.println("深克隆前："+phone);
        System.out.println("深克隆前hashcode："+phone.hashCode());
        System.out.println("深克隆前parameter的hashcode："+phone.getParameter().hashCode());
        HuaweiPhone clone = (HuaweiPhone) phone.clone();
        System.out.println("深克隆后："+clone);
        System.out.println("深克隆后hashcode："+clone.hashCode());
        System.out.println("深克隆后parameter的hashcode："+clone.getParameter().hashCode());
        phone.getParameter().setCameraPixels("4800万");
        System.out.println("修改克隆前的引用属性cameraPixels克隆后的："+clone.getParameter().getCameraPixels());
    }
}
