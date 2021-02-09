package com.hat.javaadvance.design_pattern.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体中介者，电信运营商
 */
public class Telecom implements Operators{
    List<User> numbers = new ArrayList<>();

    @Override
    public void register(User user) {
        // 给注册的用户设置中介者
        user.setOperator(this);
        // 中介者保存注册的用户
        numbers.add(user);
        System.out.println("[中国电信]提醒：["+user.getName()+"] 已成功注册手机号码 "+user.getNumber());
    }

    @Override
    public void relay(User from,User to) {
        // 打电话人和接电话人都注册到运营商才能成功打电话
        if (numbers.contains(from) && numbers.contains(to)){
            to.fromCall(from);
        }
    }
}
