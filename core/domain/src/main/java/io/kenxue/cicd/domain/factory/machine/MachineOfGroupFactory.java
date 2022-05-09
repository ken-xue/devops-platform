package io.kenxue.cicd.domain.factory.machine;

import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
public class MachineOfGroupFactory {
    public static MachineOfGroup getMachineOfGroup(){
        return new MachineOfGroup();
    }
}
