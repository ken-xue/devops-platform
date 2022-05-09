package io.kenxue.cicd.domain.factory.machine;

import io.kenxue.cicd.domain.domain.machine.MachineGroup;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
public class MachineGroupFactory {
    public static MachineGroup getMachineGroup(){
        return new MachineGroup();
    }
}
