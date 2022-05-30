package io.kenxue.devops.coreclient.dto.pipeline.pipelinenode;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@Accessors(chain = true)
public class PipelineNodeListQry extends CommonCommand {
    /**
     * 唯一标示做关联
     */
    private String parentUuid;
    /**
     * 标签
     */
    private String label;
    /**
     * 描述
     */
    private String nodDescription;
    /**
     * 图标
     */
    private String icon;
    /**
     * 节点类型：目录 or 节点
     */
    private Integer type;
}
