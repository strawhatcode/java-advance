package com.hat.javaadvance.mybatis.java;

import com.hat.javaadvance.mybatis.dao.MyBatisDao;
import com.hat.javaadvance.mybatis.mapper.QueryMapper;
import org.apache.ibatis.builder.xml.XMLMapperBuilder;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InitMybatis {
    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
//        fromApi();
        fromXml();
    }

    private static void fromApi() throws IOException {
        PooledDataSource pooledDataSource = new PooledDataSource("com.mysql.cj.jdbc.Driver",
                "jdbc:mysql://127.0.0.1:3306/distributed?characterEncoding=utf-8&serverTimezone=UTC",
                "root", "caomao");
        Environment environment = new Environment("development",new JdbcTransactionFactory(),pooledDataSource);
        Configuration configuration = new Configuration(environment);
        String resource = "mapper/QueryDistMapper.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        XMLMapperBuilder mapperParser = new XMLMapperBuilder(inputStream, configuration, resource, configuration.getSqlFragments());
        mapperParser.parse();
        SqlSessionFactory build = new DefaultSqlSessionFactory(configuration);
        SqlSession sqlSession = build.openSession();
        QueryMapper o = sqlSession.getMapper(QueryMapper.class);
        List<MyBatisDao> myBatisDaos = o.queryAge(111);
        System.out.println(Arrays.asList(myBatisDaos));
        sqlSession.close();
    }

    private static void fromXml() throws IOException {
        String source = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(source);
        SqlSessionFactory build = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = build.openSession();
        MyBatisDao o = sqlSession.selectOne("queryAge", 111);
        System.out.println(o);
    }
}
