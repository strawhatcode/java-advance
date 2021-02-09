package com.hat.javaadvance.design_pattern.command;

/**
 * 具体命令，控制台灯开关的命令
 */
public class LampCommand extends AbstractCommand {
    private Lamp lamp;

    public LampCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    public void setLamp(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    protected void open() {
        lamp.onOrOff("on");
    }

    @Override
    protected void stop() {
        lamp.onOrOff("off");
    }
}
