package io.kenxue.devops.infrastructure.repositoryimpl.machine;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupDeleteCmd;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupListQry;
import io.kenxue.devops.coreclient.dto.machine.machineofgroup.MachineOfGroupPageQry;
import io.kenxue.devops.domain.domain.machine.MachineOfGroup;
import io.kenxue.devops.domain.repository.machine.MachineOfGroupRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.convertor.MachineOfGroup2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.dataobject.MachineOfGroupDO;
import io.kenxue.devops.infrastructure.repositoryimpl.machine.database.mapper.MachineOfGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:46:28
 */
@Slf4j
@Component
public class MachineOfGroupRepositoryImpl implements MachineOfGroupRepository {

    @Resource
    private MachineOfGroupMapper machineOfGroupMapper;
    @Resource
    private MachineOfGroup2DOConvector machineOfGroup2DOConvector;

    public void create(MachineOfGroup machineOfGroup){
            machineOfGroupMapper.insert(machineOfGroup2DOConvector.toDO(machineOfGroup));
    }

    public void update(MachineOfGroup machineOfGroup){
            machineOfGroupMapper.updateById(machineOfGroup2DOConvector.toDO(machineOfGroup));
    }

    public MachineOfGroup getById(Long id){
        return machineOfGroup2DOConvector.toDomain(machineOfGroupMapper.selectById(id));
    }

    @Override
    public List<MachineOfGroup> list(MachineOfGroupListQry machineOfGroupListQry) {
        return machineOfGroup2DOConvector.toDomainList(machineOfGroupMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<MachineOfGroup> page(MachineOfGroupPageQry qry) {
        QueryWrapper<MachineOfGroupDO> qw = new QueryWrapper<>();
        IPage doPage = machineOfGroupMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),machineOfGroup2DOConvector.toDomainList(doPage.getRecords()));
    }

    @Override
    public void delete(MachineOfGroupDeleteCmd machineOfGroup) {
        machineOfGroupMapper.delete(new QueryWrapper<MachineOfGroupDO>().eq("machine_uuid",machineOfGroup.getMachineUuid()));
    }
}
