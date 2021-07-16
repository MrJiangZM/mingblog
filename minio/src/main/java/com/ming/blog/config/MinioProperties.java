package com.ming.blog.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("minio")
@Data
@Component
public class MinioProperties {
  private String url;
  private String accessKey;
  private String secretKey;
  private String defaultFolder;
}