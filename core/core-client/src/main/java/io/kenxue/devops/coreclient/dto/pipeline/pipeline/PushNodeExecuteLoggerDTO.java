package io.kenxue.devops.coreclient.dto.pipeline.pipeline;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
public class PushNodeExecuteLoggerDTO {
    private String logger;
}
