package io.kenxue.devops.infrastructure.repositoryimpl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoDTO;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoListQry;
import io.kenxue.devops.coreclient.dto.application.applicationinfo.ApplicationInfoPageQry;
import io.kenxue.devops.domain.domain.application.ApplicationInfo;
import io.kenxue.devops.domain.repository.application.ApplicationInfoRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor.ApplicationInfo2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationInfoDO;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.mapper.ApplicationInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Component
public class ApplicationInfoRepositoryImpl implements ApplicationInfoRepository {

    @Resource
    private ApplicationInfoMapper applicationInfoMapper;
    @Resource
    private ApplicationInfo2DOConvector applicationInfo2DOConvector;

    public void create(ApplicationInfo applicationInfo){
            applicationInfoMapper.insert(applicationInfo2DOConvector.toDO(applicationInfo));
    }

    public void update(ApplicationInfo applicationInfo){
            applicationInfoMapper.updateById(applicationInfo2DOConvector.toDO(applicationInfo));
    }

    public ApplicationInfo getById(Long id){
        return applicationInfo2DOConvector.toDomain(applicationInfoMapper.selectById(id));
    }

    @Override
    public List<ApplicationInfo> list(ApplicationInfoListQry applicationInfoListQry) {
        return applicationInfo2DOConvector.toDomainList(applicationInfoMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<ApplicationInfo> page(ApplicationInfoPageQry qry) {
        QueryWrapper<ApplicationInfoDO> qw = new QueryWrapper<>();
        ApplicationInfoDTO qryDTO = qry.getApplicationInfoDTO();
        if(Objects.nonNull(qryDTO)&& StringUtils.isNotBlank(qryDTO.getApplicationName()))qw.like("application_name",qryDTO.getApplicationName());
        if(Objects.nonNull(qryDTO)&& StringUtils.isNotBlank(qryDTO.getProjectUuid()))qw.eq("project_uuid",qryDTO.getProjectUuid());
        IPage doPage = applicationInfoMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),applicationInfo2DOConvector.toDomainList(doPage.getRecords()));
    }

    @Override
    public ApplicationInfo getByUuid(String applicationUuid) {
        return applicationInfo2DOConvector.toDomain(applicationInfoMapper.selectOne(new QueryWrapper<ApplicationInfoDO>().eq("uuid",applicationUuid)));
    }
}
