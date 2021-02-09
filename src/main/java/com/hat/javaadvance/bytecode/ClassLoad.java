package com.hat.javaadvance.bytecode;

public class ClassLoad {
    private int a;
    private final String b = "3";
    public static String ee = "测试";


    protected void test(){
        String c = new String("4");
        a = a + 1;
    }

    public static String teststtt(){
        String ff = ee + "静态stttt";
        return ff;
    }
}
