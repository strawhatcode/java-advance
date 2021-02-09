package com.hat.javaadvance.mybatis;

import com.hat.javaadvance.mybatis.dao.MyBatisDao;
import com.hat.javaadvance.mybatis.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class TestMybatis {
    @Autowired
    QueryService queryService;

//    @PostConstruct
    public void te(){
        List<MyBatisDao> objects = queryService.queryAge(20);
        System.out.println(Arrays.asList(objects));
    }
}
