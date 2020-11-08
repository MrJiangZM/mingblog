package com.ming.blog.one;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Jiang Zaiming
 * @date 2020/1/14 2:54 下午
 */
public class MyThreadThree implements Callable {

    @Override
    public Object call() throws Exception {
        Thread.sleep(5000L);
        System.out.println("---------");
        return "大家好啊，你是什么意思";
    }

    public static void main(String[] args) throws Exception {
        MyThreadThree myThreadThree = new MyThreadThree();
        FutureTask thread = new FutureTask<>(myThreadThree);
        Thread thread1 = new Thread(thread);
        thread1.start();
        System.out.println("999999999");
//        System.out.println(call);
        System.out.println("======" + thread.get());
    }

}
