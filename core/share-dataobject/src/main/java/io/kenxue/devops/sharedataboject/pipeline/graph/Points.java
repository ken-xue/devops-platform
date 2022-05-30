package io.kenxue.devops.sharedataboject.pipeline.graph;


import lombok.experimental.Accessors;

import java.util.List;

/**
 * 点
 */
@lombok.Data
@Accessors(chain = true)
public class Points {
    private List<String> targets;//表示当前节点作为target（有多少条线输入）
    private List<String> sources;//表示当前节点作为source（有多少条线输出）
}
