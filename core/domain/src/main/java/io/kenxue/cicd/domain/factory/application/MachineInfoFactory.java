package io.kenxue.cicd.domain.factory.application;

import io.kenxue.cicd.domain.domain.machine.MachineInfo;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
public class MachineInfoFactory {
    public static MachineInfo getMachineInfo(){
        return new MachineInfo();
    }
}
