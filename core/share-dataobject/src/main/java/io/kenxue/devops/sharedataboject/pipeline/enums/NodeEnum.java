package io.kenxue.devops.sharedataboject.pipeline.enums;

public enum NodeEnum {

    START("START","开始节点"),
    END("END","结束节点");

    private String name;
    private String desc;

    NodeEnum(String name,String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
}
