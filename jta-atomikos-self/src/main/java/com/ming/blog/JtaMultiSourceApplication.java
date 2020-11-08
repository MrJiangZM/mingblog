package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableTransactionManagement
@SpringBootApplication
        (exclude = {DataSourceAutoConfiguration.class})
public class JtaMultiSourceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(JtaMultiSourceApplication.class, args);
        log.info("app start success =============================================");
    }

}
