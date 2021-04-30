package com.ming.blog.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Future;

/**
 * @author Jiang Zaiming
 * @date 2020/5/29 4:50
 */
@Service
public class TestService {

    //    @Async
    public Integer execute01() {
        int i = 1 / 0;
        System.out.println("execute01++++++++++++++++++++++");
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute01======================");
        return 1;
    }

    public Integer execute02() {
        System.out.println("execute02++++++++++++++++++++++");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute02======================");
        return 2;
    }

    @Async
    public void execute01Async() {
        System.out.println("execute01++++++++++++++++++++++");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute01======================");
    }

    @Async
    public void execute02Async() {
        System.out.println("execute02++++++++++++++++++++++");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute02======================");
    }

    @Async
    public Future<Integer> execute01AsyncWithFuture() {
        int i = 1 / 0;
        return AsyncResult.forValue(this.execute01());
    }

    @Async
    public Future<Integer> execute02AsyncWithFuture() {
        return AsyncResult.forValue(this.execute02());
    }

    @Async
    public ListenableFuture<Integer> execute01AsyncWithListenableFuture() {
        try {
            return AsyncResult.forValue(this.execute01());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }

    @Async
    public ListenableFuture<Integer> execute02AsyncWithListenableFuture() {
        try {
            return AsyncResult.forValue(this.execute02());
        } catch (Throwable ex) {
            return AsyncResult.forExecutionException(ex);
        }
    }

    public static void main(String[] args) {
//        continueTest();
        breakTest();
    }

    private static void continueTest() {
        outer:
        for(int i = 0; i<5; i++){
            for(int j = 0; j<6; j++){
                if(j == 4){
                    continue outer;
                }
                System.out.println(j);
            }
            System.out.println("test");
        }
        System.out.println("finish");
    }

    private static void breakTest() {
        outer: {
            inner: {
                for (int i = 0; i < 3; i++) {
                    for(int j = 0; j < 5; j++){
                        if(j==4){
                            break outer;
                        }
                        System.out.println(j);
                    }
                    System.out.println("test");
                }
                System.out.println("finish");
            }
            System.out.println("outer");
        }

    }


}
