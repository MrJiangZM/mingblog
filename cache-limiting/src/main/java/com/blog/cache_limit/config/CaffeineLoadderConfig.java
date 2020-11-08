package com.blog.cache_limit.config;

import com.github.benmanes.caffeine.cache.CacheLoader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;

@Slf4j
@Configuration
public class CaffeineLoadderConfig {

    /**
     * 生效条件
     * 1、在CacheManager中引用
     * 2、有效的声明refreshAfterWrite
     * 3、不定义CacheManager
     *
     * 关于refreshAfterWrite和CacheLoader，只有当该key被调用之后才会执行这里
     * */
    @Bean
    public CacheLoader<Object,Object> cacheLoader(){
        CacheLoader<Object,Object> cacheLoader = new CacheLoader<Object, Object>() {
            @Override
            public Object load( Object key) throws Exception{
                System.out.println(System.currentTimeMillis()+" This is load key = " + key);
                throw new Exception();
//                if (String.valueOf(key).equals("kun")){
//                    return "kun kun ni hao";
//                }
//                return key + "ni hao";
            }

            @Override
            public Object reload(Object key, Object oldValue) throws Exception {
                System.out.println(System.currentTimeMillis()+" oldValue = " + oldValue);
                return this.load(key);
//                return oldValue+" a";
            }

            /**
             * 只要配置了这个方法，必定会先于reload执行
             * */
            @Override
            public CompletableFuture asyncReload(Object key, Object oldValue, Executor executor) {
                log.info("asyncReload key = {}, oldValue = {}",key,oldValue);

/*
                requireNonNull(key);
                requireNonNull(executor);
*/
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        log.info("start to reload");
                        return reload(key, oldValue);
                    } catch (RuntimeException e) {
                        throw e;
                    } catch (Exception e) {
                        throw new CompletionException(e);
                    }
                }, executor);
            }

            @Override
            public CompletableFuture asyncLoad(Object key,  Executor executor) {
                System.out.println(System.currentTimeMillis()+" asyncLoadkey = " + key);
                return CompletableFuture.supplyAsync(() -> {
                    try {
                        return this.load(key);
                    } catch (RuntimeException var3) {
                        throw var3;
                    } catch (Exception var4) {
                        throw new CompletionException(var4);
                    }
                }, executor);
            }

        };
        return cacheLoader;
    }

}
