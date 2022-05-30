package io.kenxue.devops.sharedataboject.pipeline.enums;

/**
 * 节点执行状态枚举
 */
public enum NodeExecuteStatus {

    LOADING("loading","执行中"),
    SUCCESS("success","执行成功"),
    FAILED("failed","执行失败");

    private String name;
    private String desc;

    NodeExecuteStatus(String name,String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }
}
