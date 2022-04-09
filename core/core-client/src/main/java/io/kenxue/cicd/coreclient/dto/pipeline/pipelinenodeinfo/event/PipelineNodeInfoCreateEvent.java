package io.kenxue.cicd.coreclient.dto.pipeline.pipelinenodeinfo.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Data
@AllArgsConstructor
public class PipelineNodeInfoCreateEvent implements DomainEventI {
    private String uuid;
}
