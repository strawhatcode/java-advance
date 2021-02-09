package com.hat.javaadvance.mybatis.mapper;

import com.hat.javaadvance.mybatis.dao.MyBatisDao;

import java.util.List;

public interface QueryMapper {
    List<MyBatisDao> queryAge(int age);
}
