package io.kenxue.cicd.coreclient.dto.application.applicationpipeline;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import io.kenxue.cicd.sharedataboject.pipeline.Graph;
import lombok.Data;
import lombok.experimental.Accessors;

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
     * 画布流程图
     */
    private Graph graph;
}
