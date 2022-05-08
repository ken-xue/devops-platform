package io.kenxue.cicd.coreclient.dto.pipeline.pipeline;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class PipelinePageQry extends PageQuery {

    private PipelineDTO pipelineDTO;

    private String applicationUuid;

    private String pipelineName;
}
