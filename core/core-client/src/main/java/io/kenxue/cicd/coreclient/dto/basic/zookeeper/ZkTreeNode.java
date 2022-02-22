package io.kenxue.cicd.coreclient.dto.basic.zookeeper;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/229:09
 */
@Data
public class ZkTreeNode {
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
    /**
     * 节点是否展开
     */
    private boolean open;
    /**
     * 是否是叶子节点
     */
    private boolean leafFlag;
    /**
     * 是否是父级节点
     */
    private boolean parentFlag;

    private List<ZkTreeNode> children = new ArrayList<>();

}
