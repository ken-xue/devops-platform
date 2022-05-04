package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.pipeline.PipelineNode;
/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
public class PipelineNodeFactory {
    public static PipelineNode getPipelineNode(){
        return new PipelineNode();
    }
}
