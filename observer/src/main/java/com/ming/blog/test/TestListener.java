//package com.ming.blog.test;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.ApplicationListener;
//import java.util.Random;
//
///**
// * @author MrJiangZM
// * @since <pre>2021/6/4</pre>
// */
//public class TestListener implements ApplicationListener<TestEvent> {
//
//    private String name;
//    private String article;
//
//    public TestListener(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public void onApplicationEvent(TestEvent testEvent) {
//        doSomeThing(testEvent);
//    }
//
//    private void doSomeThing(TestEvent testEvent) {
//        Object source = testEvent.getSource();
//        Random random = new Random();
//        int i = random.nextInt(10);
//        try {
//            Thread.sleep(1000 * i);
//        } catch (InterruptedException e) {
//        }
//        System.out.println(source.toString() + "====" + name);
//    }
//
//
//}
