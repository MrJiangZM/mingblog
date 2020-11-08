package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jiang Zaiming
 * @date 2019/12/6 9:46 上午
 */
@Slf4j
@SpringBootApplication
public class JavaMelodyApp {

    public static void main(String[] args) {
        SpringApplication.run(JavaMelodyApp.class, args);
        log.info("app start success");
    }

}
