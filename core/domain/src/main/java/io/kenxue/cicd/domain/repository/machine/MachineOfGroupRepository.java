package io.kenxue.cicd.domain.repository.machine;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupListQry;
import io.kenxue.cicd.coreclient.dto.machine.machineofgroup.MachineOfGroupPageQry;
import io.kenxue.cicd.domain.domain.machine.MachineOfGroup;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
public interface MachineOfGroupRepository {
    void create(MachineOfGroup machineOfGroup);
    void update(MachineOfGroup machineOfGroup);
    MachineOfGroup getById(Long id);
    List<MachineOfGroup> list(MachineOfGroupListQry machineOfGroupListQry);
    Page<MachineOfGroup> page(MachineOfGroupPageQry qry);
    void delete(MachineOfGroupDeleteCmd machineOfGroup);
}
