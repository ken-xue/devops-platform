package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@TableName("pipeline_node_template")
public class PipelineNodeDO extends CommonEntity {
    /**
     * 唯一标示做关联
     */
    private String parentUuid;
    /**
     * 标识
     */
    private String name;
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
     * 是否有结果数组供前端展示
     */
    private Boolean view;
    /**
     * 节点类型：目录 or 节点
     */
    private Integer type;
}
