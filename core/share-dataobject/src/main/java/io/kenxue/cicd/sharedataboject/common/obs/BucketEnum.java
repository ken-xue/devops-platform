package io.kenxue.cicd.sharedataboject.common.obs;

public enum BucketEnum {

    PIPELINE_LOGGER("pipeline-logger-bucket","流水线日志桶"),
    PIPELINE_NODE_LOGGER("pipeline-node-logger-bucket","流水线节点日志"),
    PIPELINE_BUILD_OBJECT("pipeline-build-object-bucket","流水线节点执行产物");

    private String name;
    private String desc;

    BucketEnum(String name,String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc(){
        return desc;
    }
}
