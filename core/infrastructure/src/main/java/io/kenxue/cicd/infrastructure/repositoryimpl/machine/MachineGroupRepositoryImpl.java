package io.kenxue.cicd.infrastructure.repositoryimpl.machine;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupListQry;
import io.kenxue.cicd.coreclient.dto.machine.machinegroup.MachineGroupPageQry;
import io.kenxue.cicd.domain.domain.machine.MachineGroup;
import io.kenxue.cicd.domain.repository.machine.MachineGroupRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.convertor.MachineGroup2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.dataobject.MachineGroupDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.machine.database.mapper.MachineGroupMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 服务器分组
 * @author mikey
 * @date 2022-05-09 18:33:49
 */
@Slf4j
@Component
public class MachineGroupRepositoryImpl implements MachineGroupRepository {

    @Resource
    private MachineGroupMapper machineGroupMapper;
    @Resource
    private MachineGroup2DOConvector machineGroup2DOConvector;

    public void create(MachineGroup machineGroup){
            machineGroupMapper.insert(machineGroup2DOConvector.toDO(machineGroup));
    }

    public void update(MachineGroup machineGroup){
            machineGroupMapper.updateById(machineGroup2DOConvector.toDO(machineGroup));
    }

    public MachineGroup getById(Long id){
        return machineGroup2DOConvector.toDomain(machineGroupMapper.selectById(id));
    }

    @Override
    public List<MachineGroup> list(MachineGroupListQry machineGroupListQry) {
        QueryWrapper<MachineGroupDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        return machineGroup2DOConvector.toDomainList(machineGroupMapper.selectList(qw));
    }

    @Override
    public Page<MachineGroup> page(MachineGroupPageQry qry) {
        QueryWrapper<MachineGroupDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        IPage doPage = machineGroupMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),machineGroup2DOConvector.toDomainList(doPage.getRecords()));
    }
}
