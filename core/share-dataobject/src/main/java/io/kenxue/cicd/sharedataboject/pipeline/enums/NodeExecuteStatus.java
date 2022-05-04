package io.kenxue.cicd.sharedataboject.pipeline.enums;

/**
 * 节点执行状态枚举
 */
public enum NodeExecuteStatus {

    LOADING("执行中"),
    SUCCESS("执行成功"),
    FAILED("执行失败");
    private String desc;

    NodeExecuteStatus(String desc) {}
}
