package com.hat.javaadvance.binary;

public class BinaryOperation {
    public static void main(String[] args) {
        BinaryOperation b= new BinaryOperation();
        b.getPosition(123,2);
    }
    public int getPosition(int a, int k){
        int i = a >> k & 1;
        System.out.println(i);
        return i;
    }
}
