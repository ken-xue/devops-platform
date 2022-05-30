package io.kenxue.devops.infrastructure.repositoryimpl.pipeline;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerListQry;
import io.kenxue.devops.coreclient.dto.pipeline.pipelineexecutelogger.PipelineExecuteLoggerPageQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineExecuteLogger;
import io.kenxue.devops.domain.repository.pipeline.PipelineExecuteLoggerRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.convertor.PipelineExecuteLogger2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineExecuteLoggerDO;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.mapper.PipelineExecuteLoggerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 流水线执行记录
 * @author mikey
 * @date 2022-05-05 11:31:43
 */
@Slf4j
@Component
public class PipelineExecuteLoggerRepositoryImpl implements PipelineExecuteLoggerRepository {

    @Resource
    private PipelineExecuteLoggerMapper pipelineExecuteLoggerMapper;
    @Resource
    private PipelineExecuteLogger2DOConvector pipelineExecuteLogger2DOConvector;

    public void create(PipelineExecuteLogger pipelineExecuteLogger){
            pipelineExecuteLoggerMapper.insert(pipelineExecuteLogger2DOConvector.toDO(pipelineExecuteLogger));
    }

    public void update(PipelineExecuteLogger pipelineExecuteLogger){
            pipelineExecuteLoggerMapper.updateById(pipelineExecuteLogger2DOConvector.toDO(pipelineExecuteLogger));
    }

    public void updateByUuid(PipelineExecuteLogger logger){
        pipelineExecuteLoggerMapper.update(pipelineExecuteLogger2DOConvector.toDO(logger),new QueryWrapper<PipelineExecuteLoggerDO>().eq("uuid",logger.getUuid()));
    }

    public PipelineExecuteLogger getById(Long id){
        return pipelineExecuteLogger2DOConvector.toDomain(pipelineExecuteLoggerMapper.selectById(id));
    }

    @Override
    public List<PipelineExecuteLogger> list(PipelineExecuteLoggerListQry qry) {
        QueryWrapper<PipelineExecuteLoggerDO> qw = new QueryWrapper<>();
        if (Objects.nonNull(qry.getPipelineUuid()))qw.eq("pipeline_uuid",qry.getPipelineUuid());
        qw.orderByDesc("gmt_create");
        return pipelineExecuteLogger2DOConvector.toDomainList(pipelineExecuteLoggerMapper.selectList(qw));
    }

    @Override
    public Page<PipelineExecuteLogger> page(PipelineExecuteLoggerPageQry qry) {
        QueryWrapper<PipelineExecuteLoggerDO> qw = new QueryWrapper<>();
        qw.eq("deleted",false);
        if (Objects.nonNull(qry.getPipelineUuid()))qw.eq("pipeline_uuid",qry.getPipelineUuid());
        qw.orderByDesc("gmt_create");
        IPage doPage = pipelineExecuteLoggerMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),pipelineExecuteLogger2DOConvector.toDomainList(doPage.getRecords()));
    }
}
