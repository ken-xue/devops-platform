package io.kenxue.cicd.sharedataboject.pipeline.node;

import io.kenxue.cicd.sharedataboject.pipeline.context.ExecuteContext;
import io.kenxue.cicd.sharedataboject.pipeline.context.Result;

public interface Node {
    Result execute(ExecuteContext context);
    String getName();//节点名称必须和数据库节点对应
}
