package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author Jiang Zaiming
 * @date 2020/6/4 3:22 下午
 */
@Slf4j
@SpringBootApplication
public class RedisMQApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(RedisMQApplication.class, args);
        log.info("start success");
    }

}
