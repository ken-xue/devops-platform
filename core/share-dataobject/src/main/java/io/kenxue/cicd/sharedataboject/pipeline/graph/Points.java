package io.kenxue.cicd.sharedataboject.pipeline.graph;


import lombok.experimental.Accessors;

import java.util.List;

/**
 * 点
 */
@lombok.Data
@Accessors(chain = true)
public class Points {
    private List<String> targets;
    private List<String> sources;
}
