package com.ming.blog.service;

public interface LimitService {
    boolean judgeLimit(String cacheKey, int count, long time);
}
