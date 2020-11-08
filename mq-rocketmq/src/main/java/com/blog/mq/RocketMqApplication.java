package com.blog.mq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class RocketMqApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketMqApplication.class, args);
        log.info("start success");
    }

}
