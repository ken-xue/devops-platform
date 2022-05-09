package io.kenxue.cicd.domain.repository.machine;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupListQry;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupPageQry;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import java.util.List;
/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
public interface MachineGroupRepository {
    void create(MachineGroup machineGroup);
    void update(MachineGroup machineGroup);
    MachineGroup getById(Long id);
    List<MachineGroup> list(MachineGroupListQry machineGroupListQry);
    Page<MachineGroup> page(MachineGroupPageQry qry);
}
