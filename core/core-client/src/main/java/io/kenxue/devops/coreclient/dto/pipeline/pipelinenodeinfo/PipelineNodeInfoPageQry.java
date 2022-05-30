package io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@Accessors(chain = true)
public class PipelineNodeInfoPageQry extends PageQuery {

    private PipelineNodeInfoDTO pipelineNodeInfoDTO;

}
