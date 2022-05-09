package io.kenxue.cicd.infrastructure.repositoryimpl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoDTO;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoListQry;
import io.kenxue.cicd.coreclient.dto.machine.MachineInfoPageQry;
import io.kenxue.cicd.domain.domain.machine.MachineInfo;
import io.kenxue.cicd.domain.repository.machine.MachineInfoRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor.MachineInfo2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.MachineInfoDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper.MachineInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 服务器节点
 * @author mikey
 * @date 2022-02-07 17:55:06
 */
@Slf4j
@Component
public class MachineInfoRepositoryImpl implements MachineInfoRepository {

    @Resource
    private MachineInfoMapper machineInfoMapper;
    @Resource
    private MachineInfo2DOConvector machineInfo2DOConvector;

    public void create(MachineInfo machineInfo){
            machineInfoMapper.insert(machineInfo2DOConvector.toDO(machineInfo));
    }

    public void update(MachineInfo machineInfo){
            machineInfoMapper.updateById(machineInfo2DOConvector.toDO(machineInfo));
    }

    public MachineInfo getById(Long id){
        return machineInfo2DOConvector.toDomain(machineInfoMapper.selectById(id));
    }

    @Override
    public List<MachineInfo> list(MachineInfoListQry qry) {
        QueryWrapper<MachineInfoDO> qw = new QueryWrapper<>();
        Optional.ofNullable(qry.getName()).map(v->qw.like("name",v).or().like("ip",v));
        return machineInfo2DOConvector.toDomainList(machineInfoMapper.selectList(qw));
    }

    @Override
    public Page<MachineInfo> page(MachineInfoPageQry qry) {
        QueryWrapper<MachineInfoDO> qw = new QueryWrapper<>();
        MachineInfoDTO machineInfoDTO = qry.getMachineInfoDTO();
        if (Objects.nonNull(machineInfoDTO.getName())){
            qw.like("name",machineInfoDTO.getName()).or().like("ip",machineInfoDTO.getName());
        }
        IPage doPage = machineInfoMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),machineInfo2DOConvector.toDomainList(doPage.getRecords()));
    }

    @Override
    public MachineInfo getByUuid(String uuid) {
        QueryWrapper<MachineInfoDO> qw = new QueryWrapper<>();
        qw.eq("uuid",uuid);
        return machineInfo2DOConvector.toDomain(machineInfoMapper.selectOne(qw));
    }
}
