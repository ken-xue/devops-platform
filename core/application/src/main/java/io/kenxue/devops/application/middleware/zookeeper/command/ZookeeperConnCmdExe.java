package io.kenxue.devops.application.middleware.zookeeper.command;

import io.kenxue.devops.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperConnCmd;
import io.kenxue.devops.coreclient.exception.ZkException;
import io.kenxue.devops.coreclient.exception.code.ZkErrorCode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1414:41
 */
@Component
public class ZookeeperConnCmdExe {

    public Response execute(final ZookeeperConnCmd zookeeperConnCmd) {
        CuratorFramework curatorFramework = ZkCacheFactory.getCuratorFramework(zookeeperConnCmd.getZookeeperDTO().getUuid());
        if (null == curatorFramework){
            CuratorFramework framework = CuratorFrameworkFactory.builder()
                    .connectString(zookeeperConnCmd.getZookeeperDTO().getAddress())
                    .retryPolicy(new ExponentialBackoffRetry(zookeeperConnCmd.getZookeeperDTO().getSessionTimeout(), 1))
                    .build();
            framework.start();
            try {
                if (!framework.blockUntilConnected(zookeeperConnCmd.getZookeeperDTO().getSessionTimeout(), TimeUnit.MILLISECONDS)) {
                    framework.close();
                    throw new ZkException(ZkErrorCode.CONNECTION_ERROR);
                }
                ZkCacheFactory.putCache(zookeeperConnCmd.getZookeeperDTO().getUuid(), framework);
            } catch (Exception e) {
                framework.close();
                throw new ZkException(ZkErrorCode.CONNECTION_ERROR);
            }
        }
        return Response.success();
    }
}
