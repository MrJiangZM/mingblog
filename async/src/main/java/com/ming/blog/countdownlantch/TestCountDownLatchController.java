package com.ming.blog.countdownlantch;

import com.beust.jcommander.internal.Lists;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/21</pre>
 */
@Slf4j
@RestController
public class TestCountDownLatchController {

    @Resource
    private TaskUtil taskUtil;
    @Resource
    private Executor serviceTaskExecutor2;

    @GetMapping("test1")
    public Object test() {
        System.out.println("start task");
        CountDownLatch latch = new CountDownLatch(3);
        taskUtil.executeVoid(() -> {
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
        }, latch);

        taskUtil.executeVoid(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
        }, latch);
        taskUtil.executeVoid(() -> {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
        }, latch);
        try {
            latch.await(1000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            System.out.println("main task execute error");
            Thread.currentThread().interrupt();
        }
        System.out.println("finish task");
        return "test";
    }

    @GetMapping("test2")
    public Object test2() throws ExecutionException, InterruptedException {
        System.out.println("start task");
//        CountDownLatch latch = new CountDownLatch(1);
//        try {
//            latch.await(1, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            System.out.println("InterruptedException ================ ");
//            Thread.currentThread().interrupt();
//        }

        ListenableFuture<UserTest> user1 = taskUtil.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            UserTest userTest = new UserTest();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
            userTest.setId(100);
            return userTest;
        });

        ListenableFuture<UserTest> user2 = taskUtil.submit(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            UserTest userTest = new UserTest();
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException ================ ");
            }
            userTest.setName("this is his name");
            return userTest;
        });
        System.out.println("wait task1");
        UserTest userTest1 = user1.get();
        System.out.println("success task1 wait task2");
        UserTest userTest2 = user2.get();
        System.out.println("success task2");
        return Lists.newArrayList(userTest1, userTest2);
    }

    @GetMapping("test3")
    public Object test3() throws ExecutionException, InterruptedException {
        System.out.println("start task");
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(() -> {
            try {
                log.info("thread1->doWork");
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("thread1->end");
                //线程数-1
                latch.countDown();
            }
        });

        try {
            latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException ================ ");
            Thread.currentThread().interrupt();
        }
        return "success";
    }

    @GetMapping("test4")
    public Object test4() throws ExecutionException, InterruptedException {
        System.out.println("start task");
        CountDownLatch latch = new CountDownLatch(1);

        ExecutorService executorService = Executors.newFixedThreadPool(5);

        executorService.execute(() -> {
            try {
                log.info("thread1->doWork");
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                log.info("thread1->end");
                //线程数-1
                latch.countDown();
            }
        });


        try {
            latch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException ================ ");
            Thread.currentThread().interrupt();
        }
        return "success";
    }

    @GetMapping("test5")
    public Object test5() throws ExecutionException, InterruptedException {
        System.out.println("start task");
        CompletableFuture<UserTest> future1 = taskUtil.submitCompletableValue(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            UserTest userTest = new UserTest();
            try {
                Thread.sleep(4000L);
            } catch (InterruptedException e) {
            }
            userTest.setId(100);
            return userTest;
        });

        CompletableFuture<UserTest> future2 = taskUtil.submitCompletableValue(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            UserTest userTest = new UserTest();
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
            userTest.setName("test name");
            return userTest;
        });

//        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2);
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(future1, future2);

        return Lists.newArrayList(objectCompletableFuture.get(), future1.get(), future2.get());
    }

    @GetMapping("test6")
    public Object test6() throws ExecutionException, InterruptedException {
        System.out.println("start task");
        UserTest userTest = new UserTest();
        CompletableFuture<Void> future1 = taskUtil.submitCompletableVoid(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            try {
                Thread.sleep(2300L);
            } catch (InterruptedException e) {
            }
            userTest.setId(100);
        });

        CompletableFuture<Void> future2 = taskUtil.submitCompletableVoid(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e) {
            }
            userTest.setName("test name");
        });

        CompletableFuture<Void> future3 = taskUtil.submitCompletableVoid(() -> {
            System.out.println(Thread.currentThread().getName() + "--sub task execute");
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
            }
            userTest.setAge(200222);
        });

        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(future1, future2, future3);
//        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(future1, future2, future3);
        voidCompletableFuture.get();
        return Lists.newArrayList(userTest);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserTest {
        private Integer id;
        private String name;
        private Integer age;
    }

}
