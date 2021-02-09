package com.hat.javaadvance.design_pattern.adapter.object_adapter;

public class ObjcetAdapterMain {
    public static void main(String[] args) {
        InputAdapter adapter = new InputAdapter(new Earphone());
        adapter.inputInterface();
    }
}
