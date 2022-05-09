package com.ming.blog.three;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author MrJiangZM
 * @since <pre>2022/1/28</pre>
 */
public class TestExcutor {

    public static void main(String[] args) {
        ThreadPoolExecutor hdfThreadPoolExecutor = new ThreadPoolExecutor(
                20,
                50,
                1,
                TimeUnit.MINUTES,
                new ArrayBlockingQueue<>(5000));
        for (int i = 0; i < 100; i++) {
            System.out.println("i into000 " + i);
            int finalI = i;
            hdfThreadPoolExecutor.execute(() ->  {
                System.out.println("i into thread" + finalI);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println("error thread" + Thread.currentThread().getName());
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName());
            });

            System.out.println("========" + Thread.currentThread().getName());
//            try {
//                sleep(3000000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }

}
