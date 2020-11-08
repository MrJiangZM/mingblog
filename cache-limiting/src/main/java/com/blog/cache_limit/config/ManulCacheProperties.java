package com.blog.cache_limit.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//@Data
@Configuration
@ConfigurationProperties(prefix = "spring.cache")
public class ManulCacheProperties {
    private int initialCapacity; //初始缓存空间大小
    private long maximnumSize; //缓存的最大条数
    private long maximunmWeight; //缓存的最大权重
    private long expireAfterAccess; //最后一次写入或访问后经过固定的时间过期
    private long expireAfterWrite; //最后一次写入后经过固定的时间过期
    private long refreshAfterWrite; //写入后经过固定的时间刷新缓存
    private boolean weakKeys; // key为弱引用
    private boolean weakValues; // value为弱引用
    private boolean softValues; //value 为软引用
    private boolean recordStats; //统计功能

}
