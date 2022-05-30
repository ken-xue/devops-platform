package io.kenxue.devops.application.machine.machinegroup.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.machine.MachineGroupRepository;
import io.kenxue.devops.domain.domain.machine.MachineGroup;
import io.kenxue.devops.coreclient.dto.machine.machinegroup.MachineGroupDeleteCmd;
import io.kenxue.devops.sharedataboject.machine.enums.GroupEnum;
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
