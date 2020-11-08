package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@EnableCaching
public class CacheApiLimitingApp {

    public static void main(String[] args) {
        SpringApplication.run(CacheApiLimitingApp.class, args);
        log.info("app start success");
    }

}
