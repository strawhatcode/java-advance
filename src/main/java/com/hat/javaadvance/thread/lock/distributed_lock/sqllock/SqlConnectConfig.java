package com.hat.javaadvance.thread.lock.distributed_lock.sqllock;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnectConfig {
    private static final String url = "jdbc:mysql://localhost:3306/distributed?serverTimezone=UTC";        //数据库地址
    private static final String username = "root";        //数据库用户名
    private static final String password = "caomao";        //数据库密码
    private static final String driver = "com.mysql.cj.jdbc.Driver";        //mysql驱动

    protected static Connection getConn(){
        Connection conn = null;
        try {
            Class.forName(driver);  //加载数据库驱动
            conn = DriverManager.getConnection(url, username, password); // 连接数据库
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    protected static void closeConn(Connection conn){
        if (null != conn){
            try {
                conn.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
