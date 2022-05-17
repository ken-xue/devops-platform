package io.kenxue.cicd.application.middleware.zookeeper.command.query;

import io.kenxue.cicd.application.middleware.zookeeper.builder.ZkTreeBuilder;
import io.kenxue.cicd.application.middleware.zookeeper.constant.ZkConstant;
import io.kenxue.cicd.application.middleware.zookeeper.factory.ZkCacheFactory;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperTreeNode;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperConnCmd;
import io.kenxue.cicd.coreclient.exception.BizException;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1710:44
 */
@Component
public class ZookeeperTreeQryExe {

    public ZookeeperTreeNode execute(ZookeeperConnCmd zookeeperConnCmd) {
        try {
            CuratorFramework framework = ZkCacheFactory.getCuratorFramework(zookeeperConnCmd.getZookeeperDTO().getUuid());
            List<String> list = framework.getChildren().forPath("/");
            List<ZookeeperTreeNode> nodeList = ZkTreeBuilder.buildTree(null, list, framework);
            ZookeeperTreeNode tree = new ZookeeperTreeNode();
            tree.setId(ZkConstant.ZK_ROOT);
            tree.setLabel(ZkConstant.ZK_ROOT);
            tree.setChildren(nodeList);
            return tree;
        }catch (Exception e){
            e.printStackTrace();
            throw new BizException("get zk data exception");
        }
    }

}
