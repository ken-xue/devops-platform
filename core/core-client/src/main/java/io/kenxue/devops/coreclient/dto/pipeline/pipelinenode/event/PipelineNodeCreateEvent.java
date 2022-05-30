package io.kenxue.devops.coreclient.dto.pipeline.pipelinenode.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@AllArgsConstructor
public class PipelineNodeCreateEvent implements DomainEventI {
    private String uuid;
}
