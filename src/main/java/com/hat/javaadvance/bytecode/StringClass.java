package com.hat.javaadvance.bytecode;

public class StringClass {
    public  void testtt() {

        String strB = "aaa";
        String strA = new String("aaa");
        String intern = strA.intern();
        if (strA == strB) {
            int b;
            b = 129;
        } else {
            String sss = "bbb";
            if (strB == intern){
                int c = 1222;
            }else {
                int d = 3333;
            }
        }
        int f = 20;
        if (f > 10){
            int g = f;
        }
    }
}
