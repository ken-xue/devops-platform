package io.kenxue.cicd.domain.factory.pipeline;

import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
public class NodeExecuteLoggerFactory {
    public static NodeExecuteLogger getNodeExecuteLogger(){
        return new NodeExecuteLogger();
    }
}
