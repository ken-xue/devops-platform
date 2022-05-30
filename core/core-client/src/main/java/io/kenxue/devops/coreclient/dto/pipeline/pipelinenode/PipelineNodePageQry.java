package io.kenxue.devops.coreclient.dto.pipeline.pipelinenode;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@Accessors(chain = true)
public class PipelineNodePageQry extends PageQuery {

    private PipelineNodeDTO pipelineNodeDTO;

}
