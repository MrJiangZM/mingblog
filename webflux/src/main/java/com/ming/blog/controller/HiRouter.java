package com.ming.blog.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class HiRouter {
    @Bean
    public RouterFunction<ServerResponse> routeHi(HiHandler hiHandler) {
        return RouterFunctions
                .route(RequestPredicates.GET("/mvc/hi")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        hiHandler :: Hi);
    }
}