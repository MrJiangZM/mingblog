package com.ming.blog.one;


import java.util.Collections;

/**
 * @author Jiang Zaiming
 * @date 2020/1/14 2:41 下午
 */
public class MyThreadTwo implements Runnable {

    private int count = 5;

    @Override
    public void run() {
        synchronized (this) {
            count--;
            System.out.println("新建线程 count减法：count:" + count);

        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyThreadTwo myThreadTwo = new MyThreadTwo();
        Thread thread1 = new Thread(myThreadTwo);
        Thread thread2 = new Thread(myThreadTwo);
        Thread thread3 = new Thread(myThreadTwo);
        Thread thread4 = new Thread(myThreadTwo);
        Thread thread5 = new Thread(myThreadTwo);
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        thread5.start();
        Thread.sleep(2000L);
        System.out.println(myThreadTwo.count);
    }

}
