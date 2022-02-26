package io.kenxue.cicd.domain.domain.application;

import com.alibaba.fastjson.JSONObject;
import io.kenxue.cicd.domain.common.CommonEntity;
import io.kenxue.cicd.sharedataboject.pipeline.Graph;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationPipeline extends CommonEntity {
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

    private Graph graph;

    /**
     * 编译流程图
     */
    public void compile() {
        Assert.notNull(graph,"流程图不能为空");
        pipelineName = graph.getHead();
        String graphStr = JSONObject.toJSONString(graph);
        //TODO:验证是否正确
        this.pipelineContext = graphStr;
    }

    /**
     * 部署流程图
     */
    public void deploy(){

    }
}
