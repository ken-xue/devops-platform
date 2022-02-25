package io.kenxue.cicd.coreclient.dto.application.pipelinenode.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Data
@AllArgsConstructor
public class PipelineNodeDeleteEvent implements DomainEventI {
    private String uuid;
}
