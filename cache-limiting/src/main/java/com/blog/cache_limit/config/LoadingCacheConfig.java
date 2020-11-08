package com.blog.cache_limit.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class LoadingCacheConfig {

    @Autowired
    private CacheLoader cacheLoader;

    /**
     * 用来加载数据
     * */
    @Bean(name = "LoadingCache")
    public LoadingCache cacheManagerWithAsyncCacheLoader(){
        log.info("cacheManagerWithCacheLoading" );
        LoadingCache loadingCache = Caffeine.newBuilder()
                .recordStats()
                .build(cacheLoader);
        return loadingCache;
    }


}
