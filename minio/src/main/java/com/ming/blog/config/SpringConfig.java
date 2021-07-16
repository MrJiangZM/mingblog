package com.ming.blog.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.annotation.Resource;

@Configuration
@EnableConfigurationProperties(MinioProperties.class)
@Slf4j
public class SpringConfig {
    @Resource
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient() {
        try {
          return MinioClient.builder()
                  .endpoint(minioProperties.getUrl())
                  .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                  .build();
        } catch (Exception e) {
            log.error(e.toString());
        }
        return null;
    }

}