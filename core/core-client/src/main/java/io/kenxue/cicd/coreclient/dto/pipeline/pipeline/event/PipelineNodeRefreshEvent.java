package io.kenxue.cicd.coreclient.dto.pipeline.pipeline.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 流水线节点状态更新时间
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@AllArgsConstructor
@Accessors(chain = true)
public class PipelineNodeRefreshEvent implements DomainEventI {
    private String uuid;
    private Object data;
}
