//package com.ming.blog.test;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author MrJiangZM
// * @since <pre>2021/6/4</pre>
// */
//@Slf4j
//@Configuration
//public class ObserverConfiguration {
//
//    @Bean
//    public CommandLineRunner commandLineRunner(ApplicationContext context) {
//        return new TestEventCommandLineRunner();
//    }
//
//    @Bean
//    public TestListener testListener1(){
//        return new TestListener("test1");
//    }
//
//    @Bean
//    public TestListener testListener2(){
//        return new TestListener("test2");
//    }
//
//    @Bean
//    public TestListener testListener3(){
//        return new TestListener("test3");
//    }
//
//
//
//}
