package io.kenxue.cicd.acl.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 麦奇
 * @Date: 21-11-13 下午2:00
 */
public abstract class AbstractCacheService<T> implements CacheService {

    Map<Object, T> cacheMap = new HashMap<>();
    /**
     * 缓存加载
     */
    protected abstract void load();
    /**
     * 重新加载
     */
    protected abstract void reload();
}
