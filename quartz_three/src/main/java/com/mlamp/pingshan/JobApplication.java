package com..;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Arrays;

/**
 * @author Jiang Zaiming
 * @date 2020/3/25 11:13 上午
 */
@Slf4j
@SpringBootApplication
public class JobApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JobApplication.class, args);
        ConfigurableEnvironment environment = context.getEnvironment();
        log.info("{} is active", Arrays.toString(environment.getActiveProfiles()));
    }

}
