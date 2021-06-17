package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author MrJiangZM
 * @since <pre>2021/6/11</pre>
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class TraceApplication {

    public static void main(String[] args) {
        SpringApplication.run(TraceApplication.class, args);
        log.info("app start success");
    }

}
