package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/17</pre>
 */
@Slf4j
@SpringBootApplication
public class Disruptor3App {

    public static void main(String[] args) {
        SpringApplication.run(Disruptor3App.class, args);
        log.info("app start success");
    }

}
