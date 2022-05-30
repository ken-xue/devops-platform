package io.kenxue.devops.domain.factory.pipeline;

import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
public class PipelineExecuteLoggerFactory {
    public static PipelineExecuteLogger getPipelineExecuteLogger(){
        return new PipelineExecuteLogger();
    }
}
