package io.kenxue.cicd.application.middleware.zookeeper.command;

import io.kenxue.cicd.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.node.ZookeeperNodeDeleteCmd;
import io.kenxue.cicd.coreclient.exception.BizException;
import io.kenxue.cicd.coreclient.exception.ZkException;
import io.kenxue.cicd.coreclient.exception.code.ZkErrorCode;
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
