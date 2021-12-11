package io.kenxue.cicd.coreclient.dto.sys.menu.enums;

public enum  MenuEnum {
    CONTENTS(0,"目录"),
    MENU(1,"菜单"),
    BUTTON(2,"按钮");

    private int code;
    private String desc;

    MenuEnum(int code, String desc) {
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
