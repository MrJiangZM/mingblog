package com.ming.blog.cache.properties;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@Slf4j
public abstract class ClusterBaseCache<K> {

    @Resource
    private JedisCluster jedisCluster;

    protected abstract String getPrefix();

    protected String buildKey(K key) {
        String prefix = getPrefix();
        if (StringUtils.isNotBlank(prefix)) {
            throw new RuntimeException("缓存前缀不能为空");
        }
        if (key == null || StringUtils.isBlank(key.toString())) {
            return prefix;
        }
        return prefix + key.toString();
    }

    protected int getTTL() {
        return 30 * 24 * 60;
    }


    public void set(K key, String value) {
        jedisCluster.setex(buildKey(key), getTTL(), value);
    }

    public String get(K key) {
        return jedisCluster.get(buildKey(key));
    }

    public void delete(K key) {
        jedisCluster.del(buildKey(key));
    }

}

