package io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@Accessors(chain = true)
public class PipelineNodeInfoGetQry extends CommonCommand {

    private Long id;
    /**
     * 唯一标示做关联
     */
    private String nodeUuid;
    /**
     * 节点详细内容
     */
    private String info;
}