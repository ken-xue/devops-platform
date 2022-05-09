package io.kenxue.cicd.domain.repository.machine;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoPageQry;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import java.util.List;
/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
public interface MachineInfoRepository {
    void create(MachineInfo machineInfo);
    void update(MachineInfo machineInfo);
    MachineInfo getById(Long id);
    List<MachineInfo> list(MachineInfoListQry machineInfoListQry);
    Page<MachineInfo> page(MachineInfoPageQry qry);
    MachineInfo getByUuid(String uuid);
}
