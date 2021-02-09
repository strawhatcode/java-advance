package com.hat.javaadvance.design_pattern.facade;

/**
 * 外观模式，将复杂的子系统封装到外观角色中，然后客户端通过外观角色调用复杂的子系统
 * 该模式使客户端和子系统耦合性降低，不过违反了开闭原则，修改起来很麻烦
 *
 * 客户端类
 */
public class Main {
    public static void main(String[] args) {
        Facade facade = new Facade();
        facade.open(); // 启动
        System.out.println("----------------------");
        facade.stop(); // 关闭
    }
}
