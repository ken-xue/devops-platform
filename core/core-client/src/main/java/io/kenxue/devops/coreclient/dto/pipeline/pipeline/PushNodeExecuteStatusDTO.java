package io.kenxue.devops.coreclient.dto.pipeline.pipeline;

import io.kenxue.devops.sharedataboject.pipeline.graph.Nodes;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
public class PushNodeExecuteStatusDTO {
    private List<String> edges;
    private Nodes nodes;
}
