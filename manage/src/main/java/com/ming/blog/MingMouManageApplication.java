package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableConfigurationProperties
public class ManageApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ManageApplication.class, args);
        log.info("{} is active", Arrays.toString(context.getEnvironment().getActiveProfiles()));
    }
}
