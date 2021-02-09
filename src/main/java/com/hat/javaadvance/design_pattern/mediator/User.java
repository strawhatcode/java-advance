package com.hat.javaadvance.design_pattern.mediator;

/**
 * 抽象同事类，包含中介者、电话号码、号码持有人名字三个属性
 */
public abstract class User {
    protected Operators operator;
    private String number;
    private String name;

    public User(String number,String name) {
        this.number = number;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setOperator(Operators operator) {
        this.operator = operator;
    }

    // 打电话给某人
    public abstract void callTo(User to);
    // 收到某人的来电
    public abstract void fromCall(User from);
}
