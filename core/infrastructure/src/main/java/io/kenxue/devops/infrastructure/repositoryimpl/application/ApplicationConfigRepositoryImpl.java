package io.kenxue.devops.infrastructure.repositoryimpl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigListQry;
import io.kenxue.devops.coreclient.dto.application.applicationconfig.ApplicationConfigPageQry;
import io.kenxue.devops.domain.domain.application.ApplicationConfig;
import io.kenxue.devops.domain.repository.application.ApplicationConfigRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor.ApplicationConfig2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationConfigDO;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.mapper.ApplicationConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Component
public class ApplicationConfigRepositoryImpl implements ApplicationConfigRepository {

    @Resource
    private ApplicationConfigMapper applicationConfigMapper;
    @Resource
    private ApplicationConfig2DOConvector applicationConfig2DOConvector;

    public void create(ApplicationConfig applicationConfig){
            applicationConfigMapper.insert(applicationConfig2DOConvector.toDO(applicationConfig));
    }

    public void update(ApplicationConfig applicationConfig){
            applicationConfigMapper.updateById(applicationConfig2DOConvector.toDO(applicationConfig));
    }

    public ApplicationConfig getById(Long id){
        return applicationConfig2DOConvector.toDomain(applicationConfigMapper.selectById(id));
    }

    @Override
    public ApplicationConfig getBUuid(String uuid){
        return applicationConfig2DOConvector.toDomain(applicationConfigMapper.selectOne(new QueryWrapper<ApplicationConfigDO>().eq("uuid",uuid)));
    }

    @Override
    public List<ApplicationConfig> list(ApplicationConfigListQry applicationConfigListQry) {
        return applicationConfig2DOConvector.toDomainList(applicationConfigMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<ApplicationConfig> page(ApplicationConfigPageQry qry) {
        QueryWrapper<ApplicationConfigDO> qw = new QueryWrapper<>();
        IPage doPage = applicationConfigMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),applicationConfig2DOConvector.toDomainList(doPage.getRecords()));
    }
}
