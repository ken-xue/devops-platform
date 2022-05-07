package io.kenxue.cicd.coreclient.dto.pipeline.pipeline;

import io.kenxue.cicd.sharedataboject.pipeline.graph.Nodes;
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
