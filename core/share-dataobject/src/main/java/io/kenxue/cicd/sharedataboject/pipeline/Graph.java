package io.kenxue.cicd.sharedataboject.pipeline;

import lombok.experimental.Accessors;
import java.util.List;

/**
 * 流程图
 */
@lombok.Data
@Accessors(chain = true)
public class Graph{
    private List<Nodes> nodes;
    private List<Endpoints> endpoints;
    private List<String> edges;
    private String head;

    /**
     * 必须包含一个开始节点和结束节点
     * @return
     */
    public boolean validate() {
        return
                nodes.stream().map(node -> "START".equals(node.getName())).count() == 1
                        &&
                nodes.stream().map(node -> "END".equals(node.getName())).count() == 1;
    }
}

/**
 * 点
 */
@lombok.Data
@Accessors(chain = true)
class Endpoints {
    private String id;
    private Data data;
}

/**
 * 节点
 */
@lombok.Data
@Accessors(chain = true)
class Nodes {
    private String id;
    private String name;
    private Points points;
    private Position position;
    private Data data;
}

/**
 * 数据
 */
@lombok.Data
@Accessors(chain = true)
class Data {
    private String value;
    private String nodeName;
    private String icon;
}

/**
 * 位置
 */
@lombok.Data
@Accessors(chain = true)
class Position {
    private int left;
    private int top;
}

/**
 * 点
 */
@lombok.Data
@Accessors(chain = true)
class Points {
    private List<String> targets;
    private List<String> sources;
}
