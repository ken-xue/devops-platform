package io.kenxue.cicd.application.middleware.zookeeper.builder;

import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperTreeNode;
import io.kenxue.cicd.coreclient.exception.BizException;
import org.apache.curator.framework.CuratorFramework;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1712:32
 */
public class ZkTreeBuilder {
    public static List<ZookeeperTreeNode> buildTree(String id, List<String> list, CuratorFramework framework) {
        List<ZookeeperTreeNode> nodeList = new ArrayList<>();
        list.forEach(node -> {
            ZookeeperTreeNode treeNode = new ZookeeperTreeNode();
            treeNode.setId("/" + node);
            treeNode.setLabel("/" + node);
            treeNode.setParentId(id == null ? "/" : id + "/" + node);
            try {
                treeNode.setParentFlag(framework.getChildren().forPath(id != null ? id : "/" + node).size() > 0);
            } catch (Exception e) {
                throw new BizException("获取子节点异常");
            }
            nodeList.add(treeNode);
        });
        return nodeList;
    }
}
