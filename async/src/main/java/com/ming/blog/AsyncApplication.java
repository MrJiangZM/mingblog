package com.ming.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;
import org.springframework.scheduling.annotation.EnableAsync;
import sun.rmi.runtime.Log;

/**
 * @author Jiang Zaiming
 * @date 2020/5/29 4:48 下午
 */
@SpringBootApplication
@EnableAsync
public class AsyncApplication {

    public static void main(String[] args) {
        SpringApplication.run(AsyncApplication.class, args);
        System.out.println("start success");
    }

}
