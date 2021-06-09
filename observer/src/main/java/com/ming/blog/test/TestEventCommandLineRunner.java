//package com.ming.blog.test;
//
//import org.apache.commons.lang3.ArrayUtils;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.stereotype.Component;
//import javax.annotation.Resource;
//
///**
// * @author MrJiangZM
// * @since <pre>2021/6/7</pre>
// */
//@Component
//public class TestEventCommandLineRunner implements CommandLineRunner {
//
//    @Resource
//    private ApplicationContext context;
//
//    @Override
//    public void run(String... args) throws Exception {
//        String test;
//        if (!ArrayUtils.isEmpty(args)) {
//            test = args[0];
//        } else {
//            test = "fabuceshilea";
//        }
//        context.publishEvent(new TestEvent("diyicifabuevent"));
//    }
//}
