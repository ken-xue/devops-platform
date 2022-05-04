package io.kenxue.cicd.coreclient.dto.pipeline.pipeline;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import io.kenxue.cicd.sharedataboject.common.group.Insert;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Graph;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationPipelineDTO extends CommonDTO {
    /**
     * 应用uuid
     */
    @NotNull(message = "应用uuid不能为空",groups = Insert.class)
    private String applicationUuid;
    /**
     * 流水线名称
     */
    @NotNull(message = "流水线名字不能为空",groups = Insert.class)
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
     * 画布流程图
     */
    private Graph graph;
}
