package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jiang Zaiming
 * @date 2020/6/5 4:00 下午
 */
@Slf4j
@SpringBootApplication
public class DisruptorApplication {

    public static void main(String[] args) {
        SpringApplication.run(DisruptorApplication.class, args);
        log.info("start success");
    }

}
