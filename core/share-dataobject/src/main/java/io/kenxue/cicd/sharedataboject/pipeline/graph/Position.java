package io.kenxue.cicd.sharedataboject.pipeline.graph;


import lombok.experimental.Accessors;

/**
 * 位置
 */
@lombok.Data
@Accessors(chain = true)
class Position {
    private int left;
    private int top;
}