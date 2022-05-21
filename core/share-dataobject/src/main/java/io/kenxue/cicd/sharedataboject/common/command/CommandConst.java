package io.kenxue.cicd.sharedataboject.common.command;

/**
 * @author 麦奇
 */
public class CommandConst {

    public static final String ENTER = "\r\n";
    public static final String EXIT = "exit" + ENTER;
    public static final String SOURCE = "source-";
    public static final String TARGET = "target-";
    /**
     * 查询Linux发行版本
     * Distributor ID: Ubuntu
     * Distributor ID: CentOS
     */
    public static final String LSB_RELEASE = "lsb_release -i";
}
