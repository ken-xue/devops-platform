package io.kenxue.devops.coreclient.dto.sys.user.enums;

public enum  UserStatusEnum {

    NORMAL(0,"正常"),
    FREEZE(1,"冻结");

    private int code;
    private String desc;

    UserStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
