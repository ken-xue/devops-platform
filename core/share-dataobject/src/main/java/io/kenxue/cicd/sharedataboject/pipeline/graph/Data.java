package io.kenxue.cicd.sharedataboject.pipeline.graph;

import lombok.experimental.Accessors;

/**
 * 数据
 */
@lombok.Data
@Accessors(chain = true)
public class Data {
    private String value;
    private String nodeState;//执行状态 /warning
    private String icon;
    private Boolean view;
}