package io.kenxue.devops.application.middleware.zookeeper.command;

import io.kenxue.devops.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.ZookeeperNodeDeleteCmd;
import io.kenxue.devops.coreclient.exception.ZkException;
import io.kenxue.devops.coreclient.exception.code.ZkErrorCode;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1716:52
 */
@Component
public class ZookeeperNodeDeleteCmdExe {

    public Response execute(ZookeeperNodeDeleteCmd zookeeperNodeDeleteCmd) {
        try {
            CuratorFramework framework = ZkCacheFactory.getCuratorFramework(zookeeperNodeDeleteCmd.getUuid());
            framework.delete().forPath(zookeeperNodeDeleteCmd.getName());
        }catch (Exception e){
            e.printStackTrace();
            throw new ZkException(ZkErrorCode.CUSTOM_EXCEPTION,"节点删除失败，非叶子节点不能删除");
        }
        return Response.success();
    }
}
