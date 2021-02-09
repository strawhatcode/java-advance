package com.hat.javaadvance.thread.lock.distributed_lock.zookeeperlock;


import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ZookeeperTest {
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooWatcher zooWatcher = new ZooWatcher();
        ZooKeeper zk = zooWatcher.getZk();

        Stat exists = zk.exists("/lock", true);
        System.out.println(exists);
        System.out.println("----------------------------------");

        String s1 = zk.create("/lock", "测试~~".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("s1"+s1);
        System.out.println("----------------------------------");

        byte[] data = zk.getData("/lock", true, null);
        System.out.println(new String(data,StandardCharsets.UTF_8));
        System.out.println("----------------------------------");

        zk.setData("/lock","改变数据".getBytes(StandardCharsets.UTF_8),-1);
        byte[] data2 = zk.getData("/lock", true, null);
        System.out.println(new String(data2,StandardCharsets.UTF_8));
        System.out.println("----------------------------------");

        Stat exists2 = zk.exists("/lock/lll", true);
        System.out.println(exists2);
        System.out.println("----------------------------------");

        String s = zk.create("/lock/lll", "子节点".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("s"+s);
        System.out.println("----------------------------------");

        byte[] data3 = zk.getData("/lock/lll", true, null);
        System.out.println(new String(data3,StandardCharsets.UTF_8));
        System.out.println("----------------------------------");

        zk.setData("/lock/lll","改变子节点".getBytes(StandardCharsets.UTF_8),-1);
        List<String> children = zk.getChildren("/lock", true);
        System.out.println(children);
        System.out.println("----------------------------------");

        byte[] data5 = zk.getData("/lock/lll", true, null);
        System.out.println(new String(data5,StandardCharsets.UTF_8));
        System.out.println("----------------------------------");

        zk.delete("/lock/lll",-1);
        List<String> children2 = zk.getChildren("/lock", true);
        System.out.println(children2);
        System.out.println("----------------------------------");

        byte[] data6 = zk.getData("/lock", true, null);
        System.out.println(new String(data6,StandardCharsets.UTF_8));
        System.out.println("----------------------------------");

    }
}
