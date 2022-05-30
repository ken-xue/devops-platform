package io.kenxue.devops.application.middleware.zookeeper.command.query;

import io.kenxue.devops.application.middleware.zookeeper.builder.ZkTreeBuilder;
import io.kenxue.devops.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperTreeNode;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.node.ZookeeperLazyLeafCmd;
import io.kenxue.devops.coreclient.exception.BizException;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1711:39
 */
@Component
public class ZookeeperLazyLeafExe {

    public List<ZookeeperTreeNode> execute(ZookeeperLazyLeafCmd zookeeperLazyLeafCmd) {
        try {
            CuratorFramework framework = ZkCacheFactory.getCuratorFramework(zookeeperLazyLeafCmd.getZookeeperLazyLeafDTO().getUuid());
            List<String> list = framework.getChildren().forPath(zookeeperLazyLeafCmd.getZookeeperLazyLeafDTO().getId());
            return ZkTreeBuilder.buildTree(zookeeperLazyLeafCmd.getZookeeperLazyLeafDTO().getId(), list);
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("叶子节点懒加载失败");
        }
    }
}
