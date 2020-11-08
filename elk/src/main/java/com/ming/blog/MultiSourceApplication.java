package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@Slf4j
//@EnableAutoConfiguration
@EnableScheduling
@SpringBootApplication
public class MultiSourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MultiSourceApplication.class, args);
        log.info("app start success");
    }

}
