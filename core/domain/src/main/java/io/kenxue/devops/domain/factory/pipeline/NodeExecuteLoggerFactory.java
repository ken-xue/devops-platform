package io.kenxue.devops.domain.factory.pipeline;

import io.kenxue.devops.domain.domain.pipeline.NodeLogger;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
public class NodeExecuteLoggerFactory {
    public static NodeLogger getNodeExecuteLogger(){
        return new NodeLogger();
    }
}
