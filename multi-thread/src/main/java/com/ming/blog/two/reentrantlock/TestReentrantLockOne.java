package com.ming.blog.two.reentrantlock;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock.lock()方法放在try 代码块外部
 * <p>
 * lock tryLock
 * 锁等待   结合线程池使用
 *
 * @author Jiang Zaiming
 * @date 2020/1/16 11:24 上午
 */
public class TestReentrantLockOne {

    private static ReentrantLock syncLock = new ReentrantLock();

    public static void main(String[] args) {
        testLock();
    }

    public static void testLock() {
        //创建线程池
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(20);
        for (int i = 0; i < 10; i++) {
            scheduler.execute(() -> {
                long start = System.currentTimeMillis();
                //尝试加锁
                try {
                    if (syncLock.tryLock(1500L, TimeUnit.MILLISECONDS)) {
                        try {
                            long end = System.currentTimeMillis();
                            System.out.println("线程:" + Thread.currentThread().getName() + "获取锁成功了！" + "等待毫秒数：" + (end - start));
                            // 模拟业务代码 耗时
                            Thread.sleep(100);
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            //解锁
                            syncLock.unlock();
                        }
                    } else {
                        long end = System.currentTimeMillis();
                        System.out.println("线程:" + Thread.currentThread().getName() + "获取锁失败了！" + "等待毫秒数：" + (end - start));
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        scheduler.shutdown();
        //保证前面的线程都执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }

}
