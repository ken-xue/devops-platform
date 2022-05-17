package io.kenxue.cicd.application.middleware.zookeeper.command;

import io.kenxue.cicd.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.node.ZookeeperNodeAddCmd;
import io.kenxue.cicd.coreclient.exception.BizException;
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
            CuratorFramework framework = ZkCacheFactory.getCuratorFramework(zookeeperNodeAddCmd.getZookeeperAddNodeDTO().getUuid());
            String result = framework.create()
                    .creatingParentContainersIfNeeded()
                    .withMode(CreateMode.PERSISTENT)
                    .forPath(zookeeperNodeAddCmd.getZookeeperAddNodeDTO().getName(),zookeeperNodeAddCmd.getZookeeperAddNodeDTO().getData().getBytes(StandardCharsets.UTF_8));
            return Response.success(result);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("添加节点失败");
        }
    }
}
