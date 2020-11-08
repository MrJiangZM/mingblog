package com.ming.blog.cyclicbarrie;

import java.util.concurrent.*;

/**
 * cyclic barrier  循环屏障
 * 让所有线程执行到某个公共点和屏障，最后一个到达后才进行执行
 * <p>
 * CyclicBarrier是一个同步辅助类，它允许一组线程相互等待，直到到达某个公共屏障点。
 * 在涉及一组固定大小的线程的程序中，这些线程必须相互等待，此时CyclicBarrier很有用。
 * 因为该barrier在释放等待线程后可以重复使用，所以称它为循环的barrier。
 * <p>
 * CyclicBarrier 可以在一个代码块中多次使用，可以设置多个屏障节点
 * 等待人吃饭然后又等待认去网吧 详见 testOne();
 * <p>
 * 可以设置两个时间节点屏障，多次等待
 *
 * CyclicBarrier 有两个构造方法  可以指定所有线程完成后做的事情
 *
 * @author Jiang Zaiming
 * @date 2020/1/17 11:35 上午
 */
public class CyclicBarrieTestOne {

    public static void main(String[] args) {
        testOne();
    }

    private static void testOne() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                5, 5,
                10, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        CyclicBarrier cb = new CyclicBarrier(3);
        System.out.println("初始化：有" + (3 - cb.getNumberWaiting()) + "个人正在赶来餐厅");
        for (int i = 0; i < 3; i++) {   //定义3个任务，即3个人从家里赶到餐厅
            //设置用户的编号
            final int person = i;
            poolExecutor.execute(() -> {    //lambda表达式
                try {
                    //此处睡眠，模拟3个人从家里来到餐厅所花费的时间
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + "---用户" + person + "即将达到餐厅," +
                            "用户" + person + "到达餐厅了。" + "当前已有" + (cb.getNumberWaiting() + 1) + "个人到达餐厅");
                    cb.await();
                    System.out.println("三个人都到到餐厅啦，" + Thread.currentThread().getName() + "开始吃饭了");
                    //todo 吃完饭后想去网吧开黑  这里具体代码我就不写啦  留给小伙伴自己实现 >.<
                    //再次wait(),等待3个人全部到达网吧  cb是可以复用的！
                    System.out.println("三个人有不同的事，准备去网吧，" + Thread.currentThread().getName() + "开始去网吧了");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println(Thread.currentThread().getName() + "---用户" + person + "即将达到网吧," +
                            "用户" + person + "到达网吧了。" + "当前已有" + (cb.getNumberWaiting() + 1) + "个人到达网吧");
                    cb.await();
                    System.out.println("三个人都到到网吧啦，" + Thread.currentThread().getName() + "开始打游戏了");
                    //3个人都到达网吧了，开始玩游戏 playGame()...
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
        poolExecutor.shutdown();    //关闭线程池
    }

}
