package io.kenxue.devops.application.middleware.zookeeper.command;

import io.kenxue.devops.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.ZookeeperDetailInfoDTO;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.ZookeeperDetailInfoQry;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1716:00
 */
@Component
public class ZookeeperDetailInfoCmdExe {

    public ZookeeperDetailInfoDTO execute(ZookeeperDetailInfoQry zookeeperDetailInfoQry) {
        ZookeeperDetailInfoDTO zookeeperDetailInfoDTO = new ZookeeperDetailInfoDTO();
        try {
            CuratorFramework framework = ZkCacheFactory.getCuratorFramework(zookeeperDetailInfoQry.getUuid());
            byte[] data = framework.getData().forPath(zookeeperDetailInfoQry.getName());
            String s = new String(data);
            zookeeperDetailInfoDTO.setData(s);
        }catch (Exception e){
            e.printStackTrace();
        }
        return zookeeperDetailInfoDTO;
    }
}
