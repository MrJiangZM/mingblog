package com.ming.blog.service;

import com.ming.blog.cache.basepack.BaseHashCache;

public class RedisHash extends BaseHashCache<String, String, Object> {
    @Override
    protected String getPrefix() {
        return "ming:hash:";
    }
}
