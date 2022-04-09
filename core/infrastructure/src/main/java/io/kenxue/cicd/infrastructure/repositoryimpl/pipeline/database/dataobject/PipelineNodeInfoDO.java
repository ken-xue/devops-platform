package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@TableName("pipeline_node_info")
public class PipelineNodeInfoDO extends CommonEntity {
    /**
     * 唯一标示做关联
     */
    private String nodeUuid;
    /**
     * 节点详细内容
     */
    private String info;
}
