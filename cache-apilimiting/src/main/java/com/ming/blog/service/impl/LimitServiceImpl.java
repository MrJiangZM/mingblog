package com.ming.blog.service.impl;

import com.ming.blog.config.CacheConfig;
import com.ming.blog.service.LimitService;
import com.ming.blog.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@Service
public class LimitServiceImpl implements LimitService {

    @Resource(name = "cacheManager")
    private CacheManager cacheManager;
    @Resource
    private CacheService cacheService;

    @Override
    public boolean judgeLimit(String cacheKey, int count, long time) {
        Cache cache = cacheManager.getCache(CacheConfig.CacheEnum.API.name());
        LinkedList<Long> apiQueue = cache.get(cacheKey) == null
                ? new LinkedList() 
                : (LinkedList<Long>) cache.get(cacheKey).get();
        long systemTime = System.currentTimeMillis();
        if (apiQueue.size() < count) {
            apiQueue.add(systemTime);
            cacheService.addCache(cacheKey, apiQueue);
            return true;
        } else {
            apiQueue.add(systemTime);
            cacheService.addCache(cacheKey, apiQueue);
            return (systemTime - apiQueue.poll()) > time;
        }
    }

}
