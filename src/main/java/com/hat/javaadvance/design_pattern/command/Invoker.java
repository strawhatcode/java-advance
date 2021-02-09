package com.hat.javaadvance.design_pattern.command;

/**
 * 调用者
 */
public class Invoker {
    AbstractCommand command;

    public Invoker(AbstractCommand command) {
        this.command = command;
    }
    public void open(){
        command.open();
    }
    public void stop(){
        command.stop();
    }
}
