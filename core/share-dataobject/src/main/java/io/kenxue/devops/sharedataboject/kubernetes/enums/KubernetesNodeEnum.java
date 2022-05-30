package io.kenxue.devops.sharedataboject.kubernetes.enums;

public enum KubernetesNodeEnum {

    MASTER("主节点"),
    WORK("工作节点");

    private String desc;

    KubernetesNodeEnum(String desc) {
        this.desc = desc;
    }

    public String getUuid() {
        return desc;
    }
}
