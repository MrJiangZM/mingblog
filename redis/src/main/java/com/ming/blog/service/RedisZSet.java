package com.ming.blog.service;

import com.ming.blog.cache.basepack.BaseZSetCache;

public class RedisZSet extends BaseZSetCache {
    @Override
    protected String getPrefix() {
        return "ming:zset:";
    }
}
