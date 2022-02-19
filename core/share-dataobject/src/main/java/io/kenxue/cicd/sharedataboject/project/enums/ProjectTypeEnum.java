package io.kenxue.cicd.sharedataboject.project.enums;

/**
 * 项目类型枚举
 */
public enum ProjectTypeEnum {

    MY(0,"我负责的项目"),
    JOIN(1,"我参加的项目");

    private int code;
    private String desc;

    ProjectTypeEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
