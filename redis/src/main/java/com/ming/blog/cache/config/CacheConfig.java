package com.ming.blog.cache.config;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.ming.blog.cache.properties.FastJsonRedisSerializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Map;
import java.util.Set;

@Slf4j
@Configuration
@EnableCaching //配置cache Manager
public class CacheConfig extends CachingConfigurerSupport {

    private static final Set<String> CACHE_NAMES
            = Sets.newHashSet("test1", "test2");


    //从配置文件中读取配置信息
    @Value("${redis.timeout}")
    private Long redisTimeout;

    /**
     * spring自动缓存管理
     * 生成key的原则 根据类名+方法名+所有参数的值生成唯一的一个key
     * 此处是利用redis代替本地缓存
     * @return
     */
    @Bean
    @Override
    public KeyGenerator keyGenerator() {
        return new KeyGenerator() {
            public Object generate(Object target, Method method, Object... params) {
                StringBuilder sb = new StringBuilder();
                sb.append(target.getClass().getName());
                sb.append(method.getName());
                for (Object obj : params) {
                    sb.append(obj.toString());
                }
                return sb.toString();
            }
        };
    }

    /**
     * 管理缓存
     * @param connectionFactory
     * @return
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {

        Assert.notNull(connectionFactory, "factory must not be null");

        RedisCacheConfiguration defaultConfig = RedisCacheConfiguration.defaultCacheConfig();
        defaultConfig = defaultConfig.disableCachingNullValues(); //不设置空值

//        RedisSerializer jacksonSerializer = new Jackson2JsonRedisSerializer(Object.class);
//        RedisSerializer jdkSerializer = new JdkSerializationRedisSerializer();
        RedisSerializer fastJsonSerializer = new FastJsonRedisSerializer(Object.class);
        defaultConfig = defaultConfig.serializeValuesWith(
                RedisSerializationContext.SerializationPair.fromSerializer(fastJsonSerializer));

        Map<String, RedisCacheConfiguration> configMap = Maps.newHashMap();
        //设置redis缓存过期时间
        configMap.put("test1", defaultConfig.entryTtl(Duration.ofHours(2)));
        configMap.put("test2", defaultConfig.entryTtl(Duration.ofHours(1)));

        RedisCacheManager cacheManager = RedisCacheManager.builder(connectionFactory)
                .initialCacheNames(CACHE_NAMES)
                .withInitialCacheConfigurations(configMap)
                .build();
        return cacheManager;
    }

    /**
     *  @Cacheable( value = "listCustomers" , key = "#length", sync = true)
     *  @Cacheable，value与上面配置的值对应，key为参数，sync=true表示同步，多个请求会被阻塞
     */

//    @Bean
//    public CacheManager cacheManager() {
//        SimpleCacheManager manager = new SimpleCacheManager();
//        ArrayList<Cache> caches = new ArrayList<>();
//
//        // a Caffeine cache used to store filterconfig from master API
//        caches.add(new CaffeineCache("test1",
//                Caffeine.newBuilder()
//                        .recordStats()
//                        .expireAfterWrite(600, TimeUnit.SECONDS)
//                        .maximumSize(1000)
//                        .build()));
//
//        caches.add(new CaffeineCache("test2",
//                Caffeine.newBuilder()
//                        //设置Cache记录状态
//                        .recordStats()
//                        //在最后一次写入缓存后开始计时，在指定的时间后过期
//                        .expireAfterWrite(600, TimeUnit.SECONDS)
//                        //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight不可以同时使用，
//                        .maximumSize(1000)
//                        .build()));
//        caches.add(new CaffeineCache("test3",
//                Caffeine.newBuilder()
//                        //设置Cache记录状态
//                        .recordStats()
//                        //在最后一次写入缓存后开始计时，在指定的时间后过期
//                        .expireAfterWrite(300, TimeUnit.SECONDS)
//                        //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight不可以同时使用，
//                        .maximumSize(1000)
//                        .build()));
//        caches.add(new CaffeineCache("test4",
//                Caffeine.newBuilder()
//                        //设置Cache记录状态
//                        .recordStats()
//                        //在最后一次写入缓存后开始计时，在指定的时间后过期
//                        .expireAfterWrite(300, TimeUnit.SECONDS)
//                        //maximumSize用来控制cache的最大缓存数量，maximumSize和maximumWeight不可以同时使用，
//                        .maximumSize(1000)
//                        .build()));
//        manager.setCaches(caches);
//        return manager;
//    }

}
