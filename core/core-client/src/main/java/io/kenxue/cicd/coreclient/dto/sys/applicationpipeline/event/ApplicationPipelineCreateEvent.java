package io.kenxue.cicd.coreclient.dto.sys.applicationpipeline.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@AllArgsConstructor
public class ApplicationPipelineCreateEvent implements DomainEventI {
    private String uuid;
}
