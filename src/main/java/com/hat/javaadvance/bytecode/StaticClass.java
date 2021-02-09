package com.hat.javaadvance.bytecode;

public class StaticClass {
    private volatile StaticClass singleton;

    private StaticClass() {
    }

    public void initObj(){
        singleton = new StaticClass();
    }

}
