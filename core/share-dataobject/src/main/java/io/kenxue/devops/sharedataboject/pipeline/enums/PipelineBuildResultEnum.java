package io.kenxue.devops.sharedataboject.pipeline.enums;

public enum PipelineBuildResultEnum {

    SUCCESS("成功"),
    FAILED("失败");

    private String desc;

    PipelineBuildResultEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
}
