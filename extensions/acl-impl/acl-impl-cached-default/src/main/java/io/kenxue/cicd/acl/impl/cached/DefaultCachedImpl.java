package io.kenxue.cicd.acl.impl.cached;

import io.kenxue.cicd.acl.cache.CacheService;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * 缺省缓存实现,可自行考虑redis,memcached等中间件
 */
@Service
public class DefaultCachedImpl implements CacheService<String,Object> {

    private ConcurrentHashMap<String, Object> cached = new ConcurrentHashMap<>(2 << 4);

    @Override
    public Object get(String key) {
        return cached.get(key);
    }

    @Override
    public void set(String key, Object value) {
        cached.put(key,value);
    }

    @Override
    public void set(String s, Object o, Long expire, TimeUnit timeUnit) {

    }
}
