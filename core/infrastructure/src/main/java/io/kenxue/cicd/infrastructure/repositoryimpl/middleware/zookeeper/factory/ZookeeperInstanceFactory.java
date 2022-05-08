package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.factory;

import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZookeeperConnectCmd;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;

/**
 * @author 刘牌
 * @Title: zk单例工厂
 * @date 2022/2/2117:58
 */
public class ZookeeperInstanceFactory {

    private static CuratorFramework curatorFramework;

    public static CuratorFramework getInstance(ZookeeperConnectCmd cmd){
        if (curatorFramework == null){
            CuratorFramework framework = CuratorFrameworkFactory.builder()
                    .connectString(cmd.getIp()+":"+cmd.getPort())
                    .retryPolicy(new BoundedExponentialBackoffRetry(5000,5000,5000))
                    .sessionTimeoutMs(60000)
                    .connectionTimeoutMs(15000)
                    .build();
            framework.start();
            curatorFramework = framework;
        }
        return curatorFramework;
    }
}
