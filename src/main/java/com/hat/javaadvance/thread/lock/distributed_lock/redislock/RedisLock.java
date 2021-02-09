package com.hat.javaadvance.thread.lock.distributed_lock.redislock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Component
public class RedisLock {
    @Value("${machine.name}")
    private String machine;  // 机器的标识，这里方便测试直接读取环境变量，这个主要是标识服务器的唯一性
    private static final long expire = 15; // 锁记录默认过期时间

    ThreadLocal<Map<String, Object>> lockInfo = new ThreadLocal<>();

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 加锁
     *
     * @param key     锁资源key值
     * @param timeout 获取锁的超时时间
     * @return 返回true 加锁成功，返回fasle 加锁失败
     */
    public boolean lock(String key, long timeout) {
        long begin = System.currentTimeMillis();
        do {
            try {
                Map<String, Object> lockMap = localLock();
                // 如果线程局部变量已存在锁的key，则执行count+1操作实现重入锁
                if (lockMap.containsKey(key)) {
                    lockMap.put(key, (Integer)lockMap.get(key) + 1);
                    return true;
                } else {
                    // redis新增锁记录
                    Boolean ok = redisTemplate.opsForValue().setIfAbsent(key,
                            machine + Thread.currentThread().getName(), expire, TimeUnit.SECONDS);
                    if (null != ok && ok) {
                        lockMap.put(key, 1); // 线程局部变量的的count设置为1
                        watchDog(key); // 启动监控守护线程
                        return true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } while (timeout == 0 || System.currentTimeMillis() - begin <= timeout);

        return false;
    }


    /**
     * 解锁
     *
     * @param key 锁的key
     */
    public void unlock(String key) {
        Map<String, Object> lockMap = localLock();
        Integer count = (Integer) lockMap.get(key);
        // 如果线程局部变量获取到的计数大于1则计数减1（重入锁）
        if (Objects.nonNull(count) && count > 1) {
            lockMap.put(key, --count);
        } else {  // 删除key
            lockInfo.remove();
            redisTemplate.delete(key);
        }
    }

    /**
     * 获取线程的threadlocal
     * @return
     */
    private Map<String, Object> localLock() {
        Map<String, Object> lockMap = lockInfo.get();
        if (Objects.isNull(lockMap)) {
            lockMap = new HashMap<>();
            lockInfo.set(lockMap);
        }
        return lockMap;
    }

    /**
     * 监控线程是否执行完，没有完的话重置key的超时时间
     *
     * @param key 锁的key
     */
    private void watchDog(String key) {
        Thread thread = Thread.currentThread(); // 获取加锁成功的线程，监控是否执行完毕
        Thread watchdog = new Thread(() -> {
            long begin = System.currentTimeMillis(); // 加锁成功后获取当前时间，开始监控线程是否执行完毕
            double border = expire * 0.7 * 1000;  // key的过期时间边界值
            while (true) {
                try {
                    // 如果得到锁的线程已经执行完毕，守护线程也退出
                    if (!thread.isAlive()){
                        return;
                    }
                    // 如果当前时间-起始时间大于key超时时间的70%则重置key的超时时间
                    if (System.currentTimeMillis() - begin > border) {
                        redisTemplate.expire(key, expire, TimeUnit.SECONDS);
                        begin = System.currentTimeMillis(); // 更新重置后的新时间
                    }
                    TimeUnit.SECONDS.sleep(2); // 2秒监控一次
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        watchdog.setDaemon(true); // 开启守护线程监控
        watchdog.start();
    }
}
