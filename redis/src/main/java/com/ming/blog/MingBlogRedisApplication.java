package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class MingBlogRedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(MingBlogRedisApplication.class, args);
        log.info("{} is active", run.getEnvironment().getActiveProfiles());
    }

}
