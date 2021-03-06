package io.kenxue.devops.application.middleware.zookeeper.command;

import io.kenxue.devops.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.ZookeeperNodeAddCmd;
import io.kenxue.devops.coreclient.exception.ZkException;
import io.kenxue.devops.coreclient.exception.code.ZkErrorCode;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author 刘牌
 * @Title: 添加zk数据
 * @date 2022-05-1713:26
 */
@Component
public class ZookeeperAddNodeCmdExe {
    public Response execute(ZookeeperNodeAddCmd zookeeperNodeAddCmd) {
        try {
            CuratorFramework framework = ZkCacheFactory.getCuratorFramework(zookeeperNodeAddCmd.getZookeeperAddOrUpdateNodeDTO().getUuid());
            String result = framework.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(zookeeperNodeAddCmd.getZookeeperAddOrUpdateNodeDTO().getName(),zookeeperNodeAddCmd.getZookeeperAddOrUpdateNodeDTO().getData().getBytes(StandardCharsets.UTF_8));
            return Response.success(result);
        }catch (Exception e){
            e.printStackTrace();
            throw new ZkException(ZkErrorCode.ADD_NODE_ERROR);
        }
    }
}
