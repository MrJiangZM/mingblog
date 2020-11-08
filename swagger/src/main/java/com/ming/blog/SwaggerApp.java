package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Jiang Zaiming
 * @date 2019/11/25 5:20 下午
 */
@Slf4j
@EnableSwagger2
@SpringBootApplication
public class SwaggerApp {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApp.class, args);
        log.info("swagger app start success");
    }

}
