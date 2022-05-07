package io.kenxue.cicd.coreclient.dto.pipeline.pipeline.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 流水线节点状态更新时间
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
@Builder
public class PipelineNodeRefreshEvent implements DomainEventI {
    private String uuid;
    private Nodes data;
    private List<String> edges;//激活或者执行完成的边
}
