package com.hat.javaadvance.design_pattern.command;

/**
 * 抽象命令，声明两种命令的方法
 */
public abstract class AbstractCommand {
    protected abstract void open();
    protected abstract void stop();
}
