package io.kenxue.cicd.application.machine.machinegroup.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupDeleteCmd;
import io.kenxue.cicd.sharedataboject.machine.enums.GroupEnum;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Component
public class MachineGroupDeleteCmdExe {
    @Resource
    private MachineGroupRepository machineGroupRepository;

    private Set<String> deleteFilterSet = new HashSet<>(
            Arrays.asList(
                    GroupEnum.BUILDER_MACHINE.getUuid()//构建服务器分组
            )
    );

    public Response execute(MachineGroupDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            MachineGroup machineGroup =machineGroupRepository.getById(id);
            if (deleteFilterSet.contains(machineGroup.getUuid()))return Response.error(String.format("%s分组无法删除",machineGroup.getName()));
            machineGroup.deleted(UserThreadContext.get());
            machineGroupRepository.update(machineGroup);
        }
        return Response.success();
    }
}
