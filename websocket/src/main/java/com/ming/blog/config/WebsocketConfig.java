package com.ming.blog.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author Jiang Zaiming
 * @date 2019/11/26 2:36 下午
 */
@Slf4j
@Configuration
public class WebsocketConfig {

    /**
     * websocket的一些基本配置
     *
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        ServerEndpointExporter endpointExporter = new ServerEndpointExporter();
//        endpointExporter.setAnnotatedEndpointClasses();
//        endpointExporter.setServerContainer();
        return new ServerEndpointExporter();
    }

}
