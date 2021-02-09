package com.hat.javaadvance.design_pattern.memento;

/**
 * 备忘录模式：
 *  为对象保存不同状态的副本，然后根据需要恢复到指定状态时的对象。
 * 优点：
 *      - 当对象的状态改变了后，可以恢复到改变之前的那种状态
 *      - 所有状态信息都保存在备忘录对象中，调用者无需知道被备忘对象的内部结构
 * 缺点：
 *      - 资源消耗大，保存一种状态相当于保存一个对象的副本。
 */
public class Client {
    public static void main(String[] args) {
        Project project = new Project();  // 创建发起人（被备份的对象）
        Caretaker caretaker = new Caretaker();  // 创建一个管理者，管理者管理备忘录对象
        project.setTag("v1");   // 发起人设置一种状态
        caretaker.add("ver1",project.createMemento());  // 将当前状态保存到备忘录对象中
        System.out.println("项目当前状态："+project.getTag());

        project.setTag("v2");
        caretaker.add("ver2",project.createMemento());
        System.out.println("项目当前状态："+project.getTag());

        project.setTag("v3");
        caretaker.add("ver3",project.createMemento());
        System.out.println("项目当前状态："+project.getTag());

        project.setTag("v4");
        caretaker.add("ver4",project.createMemento());
        System.out.println("项目当前状态："+project.getTag());

        project.setTag("v5");
        caretaker.add("ver5",project.createMemento());
        System.out.println("项目当前状态："+project.getTag());

        project.restoreMemento(caretaker.get("ver3"));   // 恢复到ver3版本
        System.out.println("项目恢复到版本："+project.getTag());

    }
}
