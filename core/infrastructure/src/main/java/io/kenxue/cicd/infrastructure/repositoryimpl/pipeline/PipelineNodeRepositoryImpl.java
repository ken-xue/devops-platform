package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.PipelineNodeListQry;
import io.kenxue.cicd.coreclient.dto.pipeline.pipelinenode.PipelineNodePageQry;
import io.kenxue.cicd.domain.domain.pipeline.PipelineNode;
import io.kenxue.cicd.domain.repository.application.PipelineNodeRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.convertor.PipelineNode2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject.PipelineNodeDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.application.database.mapper.PipelineNodeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 流水线节点类型
 * @author mikey
 * @date 2022-02-26 00:19:20
 */
@Slf4j
@Component
public class PipelineNodeRepositoryImpl implements PipelineNodeRepository {

    @Resource
    private PipelineNodeMapper pipelineNodeMapper;
    @Resource
    private PipelineNode2DOConvector pipelineNode2DOConvector;

    public void create(PipelineNode pipelineNode){
            pipelineNodeMapper.insert(pipelineNode2DOConvector.toDO(pipelineNode));
    }

    public void update(PipelineNode pipelineNode){
            pipelineNodeMapper.updateById(pipelineNode2DOConvector.toDO(pipelineNode));
    }

    public PipelineNode getById(Long id){
        return pipelineNode2DOConvector.toDomain(pipelineNodeMapper.selectById(id));
    }

    @Override
    public List<PipelineNode> list(PipelineNodeListQry pipelineNodeListQry) {
        return pipelineNode2DOConvector.toDomainList(pipelineNodeMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<PipelineNode> page(PipelineNodePageQry qry) {
        QueryWrapper<PipelineNodeDO> qw = new QueryWrapper<>();
        IPage doPage = pipelineNodeMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),pipelineNode2DOConvector.toDomainList(doPage.getRecords()));
    }
}
