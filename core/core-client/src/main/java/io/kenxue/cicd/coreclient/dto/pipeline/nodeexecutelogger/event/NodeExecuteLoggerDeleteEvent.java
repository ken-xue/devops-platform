package io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Data
@AllArgsConstructor
public class NodeExecuteLoggerDeleteEvent implements DomainEventI {
    private String uuid;
}
