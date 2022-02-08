package io.kenxue.cicd.infrastructure.repositoryimpl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoListQry;
import io.kenxue.cicd.coreclient.dto.application.machineinfo.MachineInfoPageQry;
import io.kenxue.cicd.domain.domain.application.MachineInfo;
import io.kenxue.cicd.domain.repository.application.MachineInfoRepository;
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
    public List<MachineInfo> list(MachineInfoListQry machineInfoListQry) {
        return machineInfo2DOConvector.toDomainList(machineInfoMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<MachineInfo> page(MachineInfoPageQry qry) {
        QueryWrapper<MachineInfoDO> qw = new QueryWrapper<>();
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
