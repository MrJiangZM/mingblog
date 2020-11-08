package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@Slf4j
@SpringBootApplication
public class AnnotationApplication {

    public static void main(String[] args) {
        ApplicationContext run = SpringApplication.run(AnnotationApplication.class, args);
        log.info("application run success, profile {}",
                run.getEnvironment().getActiveProfiles());
    }

}
