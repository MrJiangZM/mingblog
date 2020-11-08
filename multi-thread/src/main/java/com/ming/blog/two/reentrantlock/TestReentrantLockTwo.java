package com.ming.blog.two.reentrantlock;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition 线程等等待的使用
 *
 * @author Jiang Zaiming
 * @date 2020/1/16 11:38 上午
 */
public class TestReentrantLockTwo {

    private static Lock syncLockA = new ReentrantLock();
    private static Lock syncLockB = new ReentrantLock();
    private static Condition conditionA = syncLockA.newCondition();
    private static Condition conditionB = syncLockB.newCondition();


    public static void main(String[] args) {
        testLock();
    }

    public static void testLock() {
        //创建线程池
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(20);
        int j = 9;
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            if (finalI % 2 ==0) {
                scheduler.execute(() -> {
                    long start = System.currentTimeMillis();
                    syncLockA.lock();
                    try {
                        System.out.println("awaitBefore: " + Thread.currentThread().getName());
                        if (j == finalI) {
                            System.out.println("hahaha 睡三秒");
                            Thread.sleep(3000);
                            System.out.println("hahaha 唤醒所有当前等待的线程");
                            conditionA.signalAll();
                        }
                        conditionA.await();
                        System.out.println("awaitAfter: " + Thread.currentThread().getName());
//                    condition.
                        long end = System.currentTimeMillis();
                        System.out.println("线程:" + Thread.currentThread().getName() + "获取锁成功了！" + "等待毫秒数：" + (end - start));
                        // 模拟业务代码 耗时
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //解锁
                        syncLockA.unlock();
                    }
                });
            }
        }
        scheduler.shutdown();
        //保证前面的线程都执行完
        while (Thread.activeCount() > 2) {
            Thread.yield();
        }
    }


}
