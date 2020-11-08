package com.blog.mq.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Data
@Slf4j
@Configuration
public class RocketParamConfig implements Serializable {

    @Value("${rocket.group}")
    private String rocketGroup ;
    @Value("${rocket.topic}")
    private String rocketTopic ;
    @Value("${rocket.tag}")
    private String rocketTag ;

}
