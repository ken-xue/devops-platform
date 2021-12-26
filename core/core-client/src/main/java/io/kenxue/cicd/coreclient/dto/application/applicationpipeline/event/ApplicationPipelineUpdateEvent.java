package io.kenxue.cicd.coreclient.dto.application.applicationpipeline.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 17:17:54
 */
@Data
@AllArgsConstructor
public class ApplicationPipelineUpdateEvent implements DomainEventI {
    private String uuid;
}
