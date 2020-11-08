package com.blog.cache_limit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@EnableCaching
@SpringBootApplication
public class CurrentLimiting {

    public static void main(String[] args) {
        SpringApplication.run(CurrentLimiting.class,args);
        log.info("app start success");
    }

}
