package io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@Accessors(chain = true)
public class PipelineNodeDTO extends CommonDTO {
    /**
     * 唯一标示做关联
     */
    private String parentUuid;
    /**
     * 标签
     */
    private String label;
    /**
     * 标识
     */
    private String name;
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
    /**
     * 子节点
     */
    private List<PipelineNodeDTO> children = new ArrayList<>();
}