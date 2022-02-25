package io.kenxue.cicd.domain.domain.application;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineNode extends CommonEntity {
    /**
     * 唯一标示做关联
     */
    private String parentUuid;
    /**
     * 标签
     */
    private String label;
    /**
     * 图标
     */
    private String icon;
    /**
     * 描述
     */
    private String nodDescription;
    /**
     * 节点类型：目录 or 节点
     */
    private Integer type;
    /**
     * 子节点
     */
    private List<PipelineNode> children = new ArrayList<>();
}
