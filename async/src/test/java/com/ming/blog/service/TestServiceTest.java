package com.ming.blog.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.FailureCallback;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.util.concurrent.SuccessCallback;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestServiceTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private TestService testService;

    @Test
    public void task01() {
        long now = System.currentTimeMillis();
        logger.info("[task01][开始执行]");

        testService.execute01();
        testService.execute02();

        try {
            logger.info("[main][睡4秒]");
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task02() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task02][开始执行]");

        Future<Integer> execute01 = testService.execute01AsyncWithFuture();
        Future<Integer> execute02 = testService.execute02AsyncWithFuture();

        Integer v2 = execute02.get();
        logger.info("===== 2222 ==== " + v2);
        try {
            logger.info("[main][睡4秒]");
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Integer v1 = execute01.get();
        logger.info("===== 1111 ==== " + v1);


        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task03() throws ExecutionException, InterruptedException {
        long now = System.currentTimeMillis();
        logger.info("[task02][开始执行]");

        ListenableFuture<Integer> execute01Result = testService.execute01AsyncWithListenableFuture();
//        ListenableFuture<Integer> execute02 = testService.execute02AsyncWithListenableFuture();
        logger.info("[task04][execute01Result 的类型是：({})]", execute01Result.getClass().getSimpleName());
        // <2.1> 增加成功的回调
        // <2.1> 增加失败的回调
        execute01Result.addCallback(new SuccessCallback<Integer>() {

                                        @Override
                                        public void onSuccess(Integer result) {
                                            logger.info("[onSuccess][result: {}]", result);
                                        }

                                    },
                new FailureCallback() { // <2.1> 增加失败的回调

                    @Override
                    public void onFailure(Throwable ex) {
                        logger.info("[onFailure-===][发生异常]", ex);
                    }

                });
        // <2.2> 增加成功和失败的统一回调
        execute01Result.addCallback(new ListenableFutureCallback<Integer>() {
            @Override
            public void onSuccess(Integer result) {
                logger.info("[onSuccess][result: {}]", result);
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.info("[onFailure+----][发生异常]", ex);
            }
        });
        logger.info("======================");
        try {
            logger.info("[main][睡4秒]");
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // <3> 阻塞等待结果
        execute01Result.get();

        logger.info("[task04][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }

    @Test
    public void task04() {
        long now = System.currentTimeMillis();
        logger.info("[task01][开始执行]");

        testService.execute01();

        try {
            logger.info("[main][睡4秒]");
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        logger.info("[task01][结束执行，消耗时长 {} 毫秒]", System.currentTimeMillis() - now);
    }


}
