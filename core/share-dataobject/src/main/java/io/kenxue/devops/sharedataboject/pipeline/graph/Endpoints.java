package io.kenxue.devops.sharedataboject.pipeline.graph;

import lombok.experimental.Accessors;

/**
 * 点
 */
@lombok.Data
@Accessors(chain = true)
public class Endpoints {
    private String id;
    private Data data;
}