package io.kenxue.cicd.infrastructure.repositoryimpl.application;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelineListQry;
import io.kenxue.cicd.coreclient.dto.application.applicationpipeline.ApplicationPipelinePageQry;
import io.kenxue.cicd.domain.domain.application.ApplicationPipeline;
import io.kenxue.cicd.domain.repository.application.ApplicationPipelineRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor.ApplicationPipeline2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.ApplicationPipelineDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper.ApplicationPipelineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Slf4j
@Component
public class ApplicationPipelineRepositoryImpl implements ApplicationPipelineRepository {

    @Resource
    private ApplicationPipelineMapper applicationPipelineMapper;
    @Resource
    private ApplicationPipeline2DOConvector applicationPipeline2DOConvector;

    public void create(ApplicationPipeline applicationPipeline){
            applicationPipelineMapper.insert(applicationPipeline2DOConvector.toDO(applicationPipeline));
    }

    public void update(ApplicationPipeline applicationPipeline){
            applicationPipelineMapper.updateById(applicationPipeline2DOConvector.toDO(applicationPipeline));
    }

    public ApplicationPipeline getById(Long id){
        return applicationPipeline2DOConvector.toDomain(applicationPipelineMapper.selectById(id));
    }

    @Override
    public List<ApplicationPipeline> list(ApplicationPipelineListQry applicationPipelineListQry) {
        return applicationPipeline2DOConvector.toDomainList(applicationPipelineMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<ApplicationPipeline> page(ApplicationPipelinePageQry qry) {
        QueryWrapper<ApplicationPipelineDO> qw = new QueryWrapper<>();
        if (Objects.nonNull(qry)&&Objects.nonNull(qry.getApplicationUuid()))qw.eq("application_uuid",qry.getApplicationUuid());
        if (Objects.nonNull(qry)&&Objects.nonNull(qry.getPipelineName()))qw.like("pipeline_name",qry.getPipelineName());
        IPage doPage = applicationPipelineMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),applicationPipeline2DOConvector.toDomainList(doPage.getRecords()));
    }
}
