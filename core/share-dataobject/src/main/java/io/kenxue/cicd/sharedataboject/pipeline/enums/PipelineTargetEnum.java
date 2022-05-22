package io.kenxue.cicd.sharedataboject.pipeline.enums;

/**
 * 流水线触发类型
 */
public enum PipelineTargetEnum {

    PUSH_CODE("提交代码触发"),
    WEB_HOOK("webhook提交触发"),
    HAND("手动触发");

    private String desc;

    PipelineTargetEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

}
