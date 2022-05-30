package io.kenxue.devops.domain.factory.middleware;

import io.kenxue.devops.domain.domain.middleware.Zookeeper;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
public class ZookeeperFactory {
    public static Zookeeper getZookeeper(){
        return new Zookeeper();
    }
}
