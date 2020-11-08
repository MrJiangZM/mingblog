package com.ming.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
//扫描实体类 例如@Entity //  这行用不上
//@EntityScan("com.ming.blog.money.pojo.*.*")
public class DataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataJpaApplication.class, args);
        log.info("jpa app start success ===============================");
    }

}
