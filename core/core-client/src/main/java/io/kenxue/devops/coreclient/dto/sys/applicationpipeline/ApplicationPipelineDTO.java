package io.kenxue.devops.coreclient.dto.sys.applicationpipeline;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 16:56:03
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
     * 触发方式
     */
    private String trigger;
}
