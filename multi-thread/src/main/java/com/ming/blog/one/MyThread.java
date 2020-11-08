package com.ming.blog.one;

/**
 * 2020-01-14   调用测试简单的线程新建线程交互  等等方法
 *              几种新建线程的方法
 *              callable runnable thread
 *              sleep join wait notify notifyAll等测试
 *              wait notify notifyAll 的使用，交替打印数字字母等
 *
 *
 * @author Jiang Zaiming
 * @date 2020/1/14 2:19 下午
 */
public class MyThread extends Thread {

    private Integer count = 5;

    @Override
    public void run() {
        count--;
        System.out.println("新建了一个线程 + count:" + count);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            MyThread myThread = new MyThread();
            myThread.run();
        }
//        for (int i = 0; i < 10; i++) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    System.out.println("新建了一个线程1111");
//                    try {
//                        Thread.sleep(1000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }).start();
//        }
    }

}
