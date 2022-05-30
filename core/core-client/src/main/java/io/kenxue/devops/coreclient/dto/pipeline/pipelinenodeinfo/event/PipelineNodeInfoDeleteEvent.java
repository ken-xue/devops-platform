package io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@AllArgsConstructor
public class PipelineNodeInfoDeleteEvent implements DomainEventI {
    private String uuid;
}
