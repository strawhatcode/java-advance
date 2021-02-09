package com.hat.javaadvance.design_pattern.command;

/**
 * 具体命令，控制电视开关的命令
 */
public class TVCommand extends AbstractCommand{
    private TV tv;

    public TVCommand(TV tv) {
        this.tv = tv;
    }

    public void setTv(TV tv) {
        this.tv = tv;
    }

    @Override
    protected void open() {
        tv.onOrOff("on");
    }

    @Override
    protected void stop() {
        tv.onOrOff("off");
    }
}
