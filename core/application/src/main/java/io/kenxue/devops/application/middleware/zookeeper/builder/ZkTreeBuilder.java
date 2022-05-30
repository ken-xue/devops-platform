package io.kenxue.devops.application.middleware.zookeeper.builder;

import io.kenxue.devops.application.middleware.zookeeper.constant.ZkConstant;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperTreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1712:32
 */
public class ZkTreeBuilder {
    public static List<ZookeeperTreeNode> buildTree(String id, List<String> list) {
        List<ZookeeperTreeNode> nodeList = new ArrayList<>();
        list.forEach(node -> {
            ZookeeperTreeNode treeNode = new ZookeeperTreeNode();
            treeNode.setId("/" + node);
            treeNode.setLabel("/" + node);
            treeNode.setParentId(id == null ? ZkConstant.ZK_ROOT : id + ZkConstant.ZK_ROOT + node);
            nodeList.add(treeNode);
        });
        return nodeList;
    }
}
