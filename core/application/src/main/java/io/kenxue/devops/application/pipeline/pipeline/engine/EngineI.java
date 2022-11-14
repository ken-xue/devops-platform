package io.kenxue.devops.application.pipeline.pipeline.engine;

import io.kenxue.devops.sharedataboject.pipeline.context.ExecuteContext;

public interface EngineI {
    void execute(ExecuteContext context);
}
