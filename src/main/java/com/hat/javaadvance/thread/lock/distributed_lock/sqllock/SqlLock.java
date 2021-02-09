package com.hat.javaadvance.thread.lock.distributed_lock.sqllock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;

public class SqlLock {
    private String machine; // 机器标识
    private Connection conn; // 数据库连接对象

    public SqlLock(String machine) {
        this.machine = machine;
        conn = SqlConnectConfig.getConn();
    }

    // 加锁
    public boolean lock(String key, long timeout) {
        long begin = System.currentTimeMillis();
        // 组装机器与线程标识
        String machineThread = machine + Thread.currentThread().getName();
        // 加锁超时
        while (0 == timeout || System.currentTimeMillis() - begin < timeout) {
            try {
                // 查询当前机器和当前线程是否加过锁
                ResultSet rs = get(key);
                if (rs.next()) {
                    // 如果同一个线程尝试获得锁则实现重入锁
                    if (machineThread.equals(rs.getString("machine_thread"))) {
                        int update = 0;
                        // 循环重入加锁
                        do {
                            update = updateIncrease(key,rs.getInt("version") + 1);
                        } while (update < 1 && (0 == timeout || System.currentTimeMillis() - begin < timeout));
                        // 返回是否加锁成功
                        return update >= 1;
                    }
                } else {
                    // 插入数据库，创建锁
                    int count = insert(key,machineThread);
                    if (count > 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (Exception e) {
//                System.out.println(machine +"-"+Thread.currentThread().getName() + "加锁失败~~~，重试加锁~~~");
            }
        }
        return false;
    }

    // 释放锁
    public void unlock(String key) {
        try {
            ResultSet rs = get(key);
            if (rs.next()) {
                int version = rs.getInt("version");
                if (version > 1) {
                    int count = 0;
                    do {
                        count = updateReduce(key, version - 1);  // 如果是重入锁则version减1
                    }while (count < 1);
                } else {
                    del(key);        // 删除数据库记录
                }
            }
            System.out.println(machine+"-"+Thread.currentThread().getName() + "成功释放锁~~~~~~~~"+ LocalTime.now());
        } catch (Exception e) {
            e.printStackTrace();
            unlock(key);
        }

    }

    // 插入锁信息到数据库
    private int insert(String key,String machineThread) throws SQLException {
        String insert = "insert into sql_lock(object_name,machine_thread,version) values (?,?,?)";
        PreparedStatement psInsert = conn.prepareStatement(insert);
        psInsert.setString(1, key);
        psInsert.setString(2, machineThread);
        psInsert.setInt(3, 1);
        return psInsert.executeUpdate();
    }

    // 根据object_name和version更新锁状态(解锁使用)
    private int updateReduce(String key, int version){
        try {
            String updateByObjectName = "update sql_lock set version = ? where object_name = ?";
            PreparedStatement psUpdate = conn.prepareStatement(updateByObjectName);
            psUpdate.setInt(1, version);
            psUpdate.setString(2, key);
            return psUpdate.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    // 根据object_name和version更新锁状态(重入锁使用)
    private int updateIncrease(String key, int version) throws SQLException {
        String updateByObjectName = "update sql_lock set version = ? where object_name = ?";
        PreparedStatement psUpdate = conn.prepareStatement(updateByObjectName);
        psUpdate.setInt(1, version);
        psUpdate.setString(2, key);
        return psUpdate.executeUpdate();
    }

    // 根据object_name查询锁
    private ResultSet get(String key) throws SQLException {
        String selectByObjectName = "select * from sql_lock where object_name = ?";
        PreparedStatement ps = conn.prepareStatement(selectByObjectName);
        ps.setString(1, key);
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    // 根据object_name删除锁
    private void del(String key) {
        try {
            String del = "delete from sql_lock where object_name = ?";
            PreparedStatement psDel = conn.prepareStatement(del);
            psDel.setString(1, key);
            psDel.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            del(key);  // 如果发生异常则继续调用本身来删除key
        }
    }
}
