package io.kenxue.devops.infrastructure.repositoryimpl.pipeline;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelineListQry;
import io.kenxue.devops.coreclient.dto.pipeline.pipeline.PipelinePageQry;
import io.kenxue.devops.domain.domain.pipeline.Pipeline;
import io.kenxue.devops.domain.repository.pipeline.PipelineRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.convertor.ApplicationPipeline2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.dataobject.ApplicationPipelineDO;
import io.kenxue.devops.infrastructure.repositoryimpl.application.database.mapper.ApplicationPipelineMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
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
public class PipelineRepositoryImpl implements PipelineRepository {

    @Resource
    private ApplicationPipelineMapper applicationPipelineMapper;
    @Resource
    private ApplicationPipeline2DOConvector applicationPipeline2DOConvector;

    public void create(Pipeline pipeline){
            applicationPipelineMapper.insert(applicationPipeline2DOConvector.toDO(pipeline));
    }

    public void update(Pipeline pipeline){
            applicationPipelineMapper.updateById(applicationPipeline2DOConvector.toDO(pipeline));
    }

    public Pipeline getById(Long id){
        return applicationPipeline2DOConvector.toDomain(applicationPipelineMapper.selectById(id)).deSerializable();
    }

    @Override
    public List<Pipeline> list(PipelineListQry pipelineListQry) {
        return applicationPipeline2DOConvector.toDomainList(applicationPipelineMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<Pipeline> page(PipelinePageQry qry) {
        QueryWrapper<ApplicationPipelineDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        if (Objects.nonNull(qry)&&Objects.nonNull(qry.getApplicationUuid()))qw.eq("application_uuid",qry.getApplicationUuid());
        if (Objects.nonNull(qry)&&Objects.nonNull(qry.getPipelineName()))qw.like("pipeline_name",qry.getPipelineName());
        qw.orderBy(true,false,"gmt_create");
        IPage doPage = applicationPipelineMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),applicationPipeline2DOConvector.toDomainList(doPage.getRecords()));
    }

    @Override
    public Pipeline getByName(String name) {
        QueryWrapper<ApplicationPipelineDO> qw = new QueryWrapper<>();
        qw.eq("pipeline_name", name);
        Pipeline pipeline = applicationPipeline2DOConvector.toDomain(applicationPipelineMapper.selectOne(qw));
        pipeline.deSerializable();
        return pipeline;
    }

    @Override
    public Pipeline getByUUID(String uuid) {
        QueryWrapper<ApplicationPipelineDO> qw = new QueryWrapper<>();
        qw.eq("uuid", uuid);
        Pipeline pipeline = applicationPipeline2DOConvector.toDomain(applicationPipelineMapper.selectOne(qw));
        pipeline.deSerializable();
        return pipeline;
    }
}
