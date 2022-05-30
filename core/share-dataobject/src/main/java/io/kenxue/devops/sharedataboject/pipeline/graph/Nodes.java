package io.kenxue.devops.sharedataboject.pipeline.graph;

import io.kenxue.devops.sharedataboject.pipeline.enums.NodeExecuteStatus;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 节点
 */
@ToString
@lombok.Data
@Accessors(chain = true)
public class Nodes {

    private String id;
    private String name;
    private transient int order;
    private Points points;
    private Position position;
    private Data data;

    /**
     * 更新节点状态
     * @param status
     */
    public void refreshStatus(NodeExecuteStatus status){
        this.data.setNodeState(status.getName());
    }
}
