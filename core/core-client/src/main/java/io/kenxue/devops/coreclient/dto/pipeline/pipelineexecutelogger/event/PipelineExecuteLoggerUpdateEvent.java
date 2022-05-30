package io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Data
@AllArgsConstructor
public class PipelineExecuteLoggerUpdateEvent implements DomainEventI {
    private String uuid;
}
