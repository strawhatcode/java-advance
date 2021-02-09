package com.hat.javaadvance.mybatis.service;

import com.hat.javaadvance.mybatis.dao.MyBatisDao;
import com.hat.javaadvance.mybatis.mapper.QueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QueryService {
    @Autowired
    QueryMapper queryMapper;

    public List<MyBatisDao> queryAge(int age){
        return queryMapper.queryAge(age);
    }

}
