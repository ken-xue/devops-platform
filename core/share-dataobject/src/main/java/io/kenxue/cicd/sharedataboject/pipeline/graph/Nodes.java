package io.kenxue.cicd.sharedataboject.pipeline.graph;

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
}
