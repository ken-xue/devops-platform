package io.kenxue.devops.sharedataboject.pipeline.graph;

import lombok.experimental.Accessors;
import java.util.List;

/**
 * 流程图
 */
@lombok.Data
@Accessors(chain = true)
public class Graph{
    private List<Nodes> nodes;
    private List<Endpoints> endpoints;
    private List<String> edges;
    private String head;
}
