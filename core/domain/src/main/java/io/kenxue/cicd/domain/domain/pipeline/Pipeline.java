package io.kenxue.cicd.domain.domain.pipeline;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.cicd.domain.common.CommonEntity;
import io.kenxue.cicd.sharedataboject.pipeline.enums.NodeEnum;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Graph;
import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 流水线
 *
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pipeline extends CommonEntity {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 流水线名称
     */
    private String pipelineName;
    /**
     * 应用类型
     */
    private String pipelineContext;
    /**
     * 描述
     */
    private String description;
    /**
     * 流程图
     */
    private Graph graph;
    /**
     * 触发方式
     */
    private String triggerWay;
    /**
     * 编译流程图
     */
    public void serializable() {
        String context = JSONObject.toJSONString(graph);
        this.pipelineContext = context;
        validate();
    }

    public Pipeline deSerializable(){
        Graph graph = JSONObject.parseObject(pipelineContext,Graph.class);
        this.graph = graph;
        return this;
    }

    @Override
    public void validate() {
        Assert.notNull(graph, "流程图不能为空");
        Assert.notNull(graph.getNodes(), "节点不能为空");
        Assert.notNull(applicationUuid, "应用ID不能为空");
        Assert.hasLength(pipelineName, "流水线名字不能为空");
        int start = graph.getNodes().stream().filter(nodes -> "START".equals(nodes.getName())).collect(Collectors.toList()).size();
        int end = graph.getNodes().stream().filter(nodes -> "END".equals(nodes.getName())).collect(Collectors.toList()).size();
        Assert.isTrue(start <= 1, "必须包含一个开始节点");
        Assert.isTrue(start >= 1, "最多包含一个开始节点");
        Assert.isTrue(end <= 1, "必须包含一个结束节点");
        Assert.isTrue(end >= 1, "最多包含一个结束节点");
    }
}
