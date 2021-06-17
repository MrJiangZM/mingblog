package com.ming.blog.countdownlatch;

import java.util.concurrent.*;

/**
 * countDownLatch的基本使用
 *
 * @author Jiang Zaiming
 * @date 2020/1/17 10:17 上午
 */
public class CountDownLatchTestOne {

    public static void main(String[] args) throws Exception {
        // 简单的测试用法
        testOne();
//        testTwo();
//        testThree();
//        testFour();
    }

    /**
     * 让主线程执行结束其他线程才去工作
     * <p>
     * 同时新建一个线程用来监听其他线程，安全关闭线程池
     * <p>
     * 可以使用多个countDownLatch来协同工作，用来初始化或者执行一些操作等
     */
    private static void testFour() throws InterruptedException {
        ExecutorService executor = Executors.newScheduledThreadPool(5);
        CountDownLatch count = new CountDownLatch(1);
        CountDownLatch countTwo = new CountDownLatch(1);
        for (int i = 0; i < 5; i++) {
            executor.execute(() -> {
                try {
                    System.out.println("线程执行等待信号");
                    count.await();
                    Thread.sleep(5000L);
                    System.out.println("线程执行完了");
                    countTwo.countDown();
                } catch (InterruptedException e) {
                    System.out.println("线程执行出错了可咋整");
                }
            });
        }
        new Thread(() -> {
            try {
                System.out.println("这个线程用于等待线程池的执行，开始等待了啊");
                countTwo.await();
                executor.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("主线程用于唤醒");
        Thread.sleep(3000L);
        count.countDown();
        System.out.println("主线程唤醒成功");

    }

    /**
     * 两个countDownLatch协同工作
     * 让主线程执行完毕，然后附属线程做其他事
     */
    private static void testThree() throws Exception {
        ExecutorService executor = Executors.newScheduledThreadPool(5);
        CountDownLatch count1 = new CountDownLatch(1);
        CountDownLatch count2 = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            TestThree testThree = new TestThree(count1, count2);
            executor.execute(testThree);
        }
        count1.countDown();
        System.out.println("主线程执行结束了。。。。。。");
        count2.await();
        System.out.println("所有的执行完了啊");
    }

    /**
     * countDown方法最好放在同步块中，保证多线程下的减少问题
     * <p>
     * await 可以放在新建线程中，也可以放在主线程中
     *
     * @throws InterruptedException
     */
    private static void testTwo() throws InterruptedException {
        ExecutorService executor = Executors.newScheduledThreadPool(100);
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            CountRunnable runnable = new CountRunnable(countDownLatch);
            executor.execute(runnable);
        }
        System.out.println("开始新建线程执行具体内容了，这个是啥意思");
//        countDownLatch.await();
//        executor.shutdown();
        System.out.println("线程执行完成了，这个是啥意思");
    }

    /**
     * 这个可能会出现问题等，最好使用 synchronized 关键字
     */
    private static void testOne() {
        CountDownLatch countDownLatch = new CountDownLatch(5);
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(20);
        for (int i = 0; i < 20; i++) {
            scheduler.execute(() -> {
                try {
                    Thread.sleep(5000L);
                    System.out.println("模拟线程执行时间5second，ThreadName:" + Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        System.out.println("新建5个线程成功");
        try {
            System.out.println("主线程等待");
            countDownLatch.await();
            System.out.println("主线程重新启动");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduler.shutdown(); // 需要释放线程池的内容，
        System.out.println("主线程结束运行");
    }

}


class CountRunnable implements Runnable {

    private CountDownLatch countDownLatch;

    public CountRunnable(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
//            synchronized (countDownLatch) {
            Thread.sleep(3000L);
            System.out.println("当前线程+ " + Thread.currentThread().getName() + "  count" + countDownLatch.getCount());
            synchronized (countDownLatch) {
                countDownLatch.countDown();
                System.out.println("当前线程+ " + Thread.currentThread().getName() + "  count" + countDownLatch.getCount());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("当前线程执行完成了");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            System.out.println("报错了啊");
        }
    }
}

class TestThree implements Runnable {

    private CountDownLatch begin;
    private CountDownLatch end;

    public TestThree(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    @Override
    public void run() {
        try {
            begin.await();
            System.out.println("处于等待的线程开始自己预期工作......" + Thread.currentThread().getName());
            Thread.sleep(2000L);
            end.countDown();
            System.out.println("线程完成了自己预期工作......" + Thread.currentThread().getName());
        } catch (Exception e) {
            System.out.println("报错了啊 哈哈哈");
        }
    }
}
