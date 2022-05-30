package io.kenxue.devops.coreclient.dto.pipeline.pipeline;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class PipelineListQry extends CommonCommand {
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
}
