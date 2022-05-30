package io.kenxue.devops.coreclient.dto.middleware.zookeeper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/229:09
 */
@Data
public class ZookeeperTreeNode {
    /**
     * 节点id
     */
    private String id;
    /**
     * 父节点id
     */
    private String parentId;
    /**
     * 节点名称
     */
    private String label;

    private List<ZookeeperTreeNode> children = new ArrayList<>();

}
