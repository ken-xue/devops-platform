package io.kenxue.cicd.domain.domain.pipeline;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PipelineNodeInfo extends CommonEntity {
    /**
     * 唯一标示做关联
     */
    private String nodeUuid;
    /**
     * 节点详细内容
     */
    private String info;

    @Override
    public void validate() {
        super.validate();
    }
}
