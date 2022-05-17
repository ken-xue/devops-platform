package io.kenxue.cicd.application.middleware.zookeeper.factory;

import io.kenxue.cicd.coreclient.exception.BizException;
import org.apache.curator.framework.CuratorFramework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1710:08
 */
public class ZkCacheFactory {
    private static final Map<String, CuratorFramework> curatorFrameworkMap = new ConcurrentHashMap<>();

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
