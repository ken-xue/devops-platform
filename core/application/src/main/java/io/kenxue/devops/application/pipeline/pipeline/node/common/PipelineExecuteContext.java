package io.kenxue.devops.application.pipeline.pipeline.node.common;

import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.devops.sharedataboject.pipeline.graph.Graph;
import io.kenxue.devops.sharedataboject.pipeline.graph.Nodes;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author biaoyang
 */
@Data
@Accessors(chain = true)
public class PipelineExecuteContext extends ExecuteContext {
    /**
     * 流程图实例
     */
    private Graph graph;
    /**
     * 所有节点
     */
    private List<Nodes> nodes;
    /**
     * 执行路线
     */
    private volatile List<String> edges;
    /**
     * 开始节点
     */
    private volatile Nodes start;
    /**
     * 路线source->List<target>
     */
    private volatile Map<String, List<String>> targetLineMap = new HashMap<>(2 << 4);
    /**
     * 路线target<-List<source>
     */
    private volatile Map<String, List<String>> sourceLineMap = new HashMap<>(2 << 4);
    /**
     *
     */
    private volatile Map<String, Nodes> targetMap = new HashMap<>(2 << 4);
    /**
     *
     */
    private volatile Map<String, Nodes> sourceMap = new HashMap<>(2 << 4);

    /**
     * 当前执行的记录
     */
    private volatile PipelineExecuteLogger pipelineExecute,Logger;

    /**
     * 当前执行的应用
     */
    private ApplicationInfo application;
}
