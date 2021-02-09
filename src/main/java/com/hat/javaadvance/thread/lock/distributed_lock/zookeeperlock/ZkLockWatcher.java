package com.hat.javaadvance.thread.lock.distributed_lock.zookeeperlock;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class ZkLockWatcher implements Watcher {

    private ZooKeeper zk;
    private CountDownLatch latch = new CountDownLatch(1);

    public ZkLockWatcher() throws IOException, InterruptedException {
        this.zk = new ZooKeeper("127.0.0.1:2181", 3000000, this);
        latch.await();
    }
    public ZooKeeper getZk(){
        return zk;
    }

    @Override
    public void process(WatchedEvent event) {
        Event.EventType type = event.getType();
        Event.KeeperState state = event.getState();
        String path = event.getPath();
//        System.out.println("当前事件："+event);
//        System.out.println("当前事件类型："+type);
//        System.out.println("当前事件状态："+state);
//        System.out.println("当前路径："+path);
        if (Event.KeeperState.SyncConnected.equals(state)){
            switch (type){
                case None:
//                    System.out.println("zk客户端连接成功~~~");
                    latch.countDown();
                    break;
                case NodeCreated:
//                    System.out.println(path+"节点创建~~");
                    break;
                case NodeDeleted:
//                    System.out.println(path+"节点删除~~");
                    break;
                case NodeDataChanged:
//                    System.out.println(path+"节点数据改变~~~");
                    break;
                case DataWatchRemoved:
//                    System.out.println(path+"数据删除~~");
                    break;
                case ChildWatchRemoved:
//                    System.out.println(path+"子节点删除~~");
                    break;
                case NodeChildrenChanged:
//                    System.out.println(path+"子节点改变~~");
                    break;
                case PersistentWatchRemoved:
//                    System.out.println(path+"持久化删除~~");
                    break;
            }
        }else if (Event.KeeperState.AuthFailed.equals(state)){

//            System.out.println("客户端AuthFailed");
        }else if (Event.KeeperState.Closed.equals(state)){
//            System.out.println("客户端Closed");

        }else if (Event.KeeperState.ConnectedReadOnly.equals(state)){
//            System.out.println("客户端ConnectedReadOnly");

        }else if (Event.KeeperState.Disconnected.equals(state)){
//            System.out.println("客户端Disconnected");

        }else if (Event.KeeperState.Expired.equals(state)){
//            System.out.println("客户端Expired");

        }else if (Event.KeeperState.SaslAuthenticated.equals(state)){
//            System.out.println("客户端SaslAuthenticated");
        }
    }
}
