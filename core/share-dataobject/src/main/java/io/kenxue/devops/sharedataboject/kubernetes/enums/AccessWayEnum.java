package io.kenxue.devops.sharedataboject.kubernetes.enums;

/**
 * {value: 'TOKEN', label: '秘钥'},
 *         {value: 'CONFIG_FILE', label: '配置文件'},
 *         {value: 'UPWD', label: '用户名密码'}
 */
public enum AccessWayEnum {

    UPWD("用户名密码"),
    TOKEN("秘钥"),
    CONFIG_FILE("配置文件");

    private String desc;

    AccessWayEnum(String desc) {
        this.desc = desc;
    }
}
