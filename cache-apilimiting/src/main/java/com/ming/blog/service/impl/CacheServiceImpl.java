package com.ming.blog.service.impl;

import com.ming.blog.domain.User;
import com.ming.blog.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @Override
    @CachePut(value = "API", key = "#strKey")
    public LinkedList<Long> addCache(String strKey, LinkedList<Long> apiQueue) {
        return apiQueue;
    }

}
