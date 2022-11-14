package io.kenxue.devops.application.pipeline.pipeline.engine;

import io.kenxue.devops.domain.domain.pipeline.NodeLogger;

public interface PipelineExecute {
    NodeLogger getExecuteNode(String key);
}
