package com.hat.javaadvance.thread.lock.distributed_lock.zookeeperlock;

import org.apache.commons.lang.StringUtils;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class ZkLock {

    // 线程的加锁计数器，实现重入锁
    private ConcurrentHashMap<Thread, AtomicInteger> local = new ConcurrentHashMap<>();
    private static final String ROOT_PATH = "/zkLock"; // 根节点
    private static final String LOCK_NAME = "lock_"; // 顺序子节点前缀
    private String machine; // 机器标识

    public ZkLock(String machine) {
        this.machine = machine;
    }

    /**
     * 加锁
     *
     * @param key 锁的key唯一标识
     * @return
     */
    public boolean lock(String key) {
        // key不能为空
        if (StringUtils.isBlank(key)){
            return false;
        }
        // 重入锁的实现
        Thread thread = Thread.currentThread();
        AtomicInteger count = local.get(thread);
        if (null != count && count.get() > 0) { // 如果当前线程加过锁（count > 0）则实现重入锁，计数+1
            count.incrementAndGet();
            return true;
        }
        try {
            ZooKeeper zk = new ZkLockWatcher().getZk();  // 创建zk客户端
            if (Objects.isNull(zk)) {
                return false;
            }
            String keyNode = ROOT_PATH + "/" + key;  // 锁的节点路径
            String data = machine + thread.getName(); // 机器与线程组成唯一标识，解锁时会根据该值来删除锁节点
            String lockNode = createNode(zk, key, data.getBytes(StandardCharsets.UTF_8));   // 创建锁节点
            if (null != lockNode) {   // 创建成功
                List<String> locks = zk.getChildren(keyNode, false);  // 获取所有的子节点
                Collections.sort(locks);  // 对子节点排序，正序
                int index = locks.indexOf(lockNode.substring(keyNode.length() + 1)); // 判断当前子节点在子节点列表中的位置
                if (index == -1) {
                    return false;  // 如果当前顺序子节点不在顺序子节点列表中则加锁失败或者抛出异常
                }
                if (index != 0) {  // 不是在第一位时加锁失败，设置监听器监听前一个节点，等待前一个节点被删除
                    AtomicBoolean flag = new AtomicBoolean(false);  // 监控前一位置的子节点是否被删除
                    // 获取前一子节点
                    String preNode = keyNode + "/" + locks.get(--index);
                    // 监听前一个节点
                    zk.exists(preNode, event -> {
                        System.out.println("监听到event===  " + event);
                        // 前一个子节点删除后将flag设置为true
                        if (Watcher.Event.EventType.NodeDeleted == event.getType()) {
                            flag.set(true);
                        }
                    });
                    // 循环等待事件触发（等待前一节点释放锁）
                    while (!flag.get()) {
                    }
                    ;
                }
                // 当前线程加锁计数器加一
                local.put(thread, new AtomicInteger(1));
                return true;  // 加锁成功
            }

        } catch (IOException | InterruptedException | KeeperException ioException) {
            ioException.printStackTrace();
        }
        // 加锁失败
        return false;
    }

    /**
     * 解锁，如果key节点有多个顺序子节点，则删除当前线程的顺序子节点
     * 如果key节点只有一个顺序子节点，则删除当前线程的顺序子节点，且删除key节点
     * 如果key节点没有顺序子节点，则删除key节点
     *
     * @param key
     * @return
     * @throws IOException
     * @throws InterruptedException
     * @throws KeeperException
     */
    public boolean unlock(String key) {
        if (StringUtils.isBlank(key)){
            return false;
        }
        // 重入锁的释放锁实现
        Thread thread = Thread.currentThread();  // 获取当前线程
        AtomicInteger count = local.get(thread);  // 从加锁计数器中获取当前线程加锁的次数
        if (null != count && count.get() > 1) {  // 如果加锁次数在2或以上时只做计数器减一操作
            count.decrementAndGet();
            return true;
        }
        try {
            ZooKeeper zk = new ZkLockWatcher().getZk();  // 创建zk客户端
            if (Objects.isNull(zk)) {
                return false;
            }
            String node = ROOT_PATH + "/" + key;  // key节点
            String data = machine + thread.getName(); // 当前机器+线程唯一标识
            List<String> children = zk.getChildren(node, false);  // 获取所有子节点
            if (children.size() > 1) {  // 子节点数大于1时，即有其他线程在等待
                // 遍历找出当前线程创建的顺序子节点，并删除该顺序子节点
                for (int i = 0; i < children.size(); i++) {
                    String curNode = node + "/" + children.get(i);
                    String nodeData = new String(zk.getData(curNode, false, null), StandardCharsets.UTF_8);
                    if (data.equals(nodeData)) {
                        zk.delete(curNode, -1);
                    }
                }
                // 顺序子节点只有一个时
            } else if (children.size() == 1) {
                zk.delete(node + "/" + children.get(0), -1);
                zk.delete(node, -1);
            } else {  // 没有顺序子节点时
                zk.delete(node, -1);
            }
            local.remove(thread);  // 如果当前线程完全释放锁则将加锁计数器删除
            return true; // 删除子节点成功
        } catch (InterruptedException | KeeperException | IOException e) {
            e.printStackTrace();
        }
        return false; // 删除子节点失败
    }


    /**
     * /root/key/lock-  结构或者/root/lock-，其中/root是永久节点，/key可以null，永久节点， /lock-是临时顺序节点
     *
     * @param zk
     * @param key
     * @return
     * @throws KeeperException
     * @throws InterruptedException
     */
    private String createNode(ZooKeeper zk, String key, byte[] data) throws KeeperException, InterruptedException {
        boolean b = existsRoot(zk);  // 判断是否存在根路径
        if (b) {
            // 锁的key标识
            String keyNode = ROOT_PATH + "/" + key;
            try {
                // 判断是否存在key节点
                if (zk.exists(keyNode, false) == null) {
                    // 不存在key节点则创建
                    keyNode = zk.create(keyNode, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                }
            } catch (KeeperException.NodeExistsException e) {
                // 捕获NodeExistsException异常不做处理，相当于zk服务器已存在key节点
            }
            // 在key节点内创建临时顺序节点
            return zk.create(keyNode + "/" + LOCK_NAME, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        }
        // 创建失败时返回null
        return null;
    }

    /**
     * 判断是否存在根节点
     *
     * @param zk
     * @return
     */
    private boolean existsRoot(ZooKeeper zk) {
        String rootNode;
        try {
            // 存在根节点返回true
            Stat stat = zk.exists(ROOT_PATH, false);
            if (null != stat) {
                return true;
            }
            // 当不存在根节点时，创建根节点，成功创建返回true
            rootNode = zk.create(ROOT_PATH, null, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            if (null != rootNode) {
                return true;
            }
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
        // 没有根节点且创建失败返回false
        return false;
    }
}
