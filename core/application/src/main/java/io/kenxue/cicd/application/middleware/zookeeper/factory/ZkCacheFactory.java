package io.kenxue.cicd.application.middleware.zookeeper.factory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.apache.curator.framework.CuratorFramework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1710:08
 */
public class ZkCacheFactory {

    private static final Map<String, CuratorFramework> curatorFrameworkMap = new ConcurrentHashMap<>();

    private static final Cache<String, CuratorFramework> curatorFrameworkCached = CacheBuilder.newBuilder().expireAfterAccess(5, TimeUnit.MINUTES).build();

    public static CuratorFramework getCuratorFramework(String uuid) {
        return curatorFrameworkMap.get(uuid);
    }

    public static void putCache(String uuid, CuratorFramework curatorFramework) {
        curatorFrameworkMap.put(uuid, curatorFramework);
    }

    public static void clearCache(String uuid) {
        curatorFrameworkMap.remove(uuid);
    }
}
