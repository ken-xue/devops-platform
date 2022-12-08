package io.kenxue.devops.infrastructure.repositoryimpl.pipeline;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoListQry;
import io.kenxue.devops.coreclient.dto.pipeline.pipelinenodeinfo.PipelineNodeInfoPageQry;
import io.kenxue.devops.domain.domain.pipeline.PipelineNodeInfo;
import io.kenxue.devops.domain.repository.pipeline.PipelineNodeInfoRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.convertor.PipelineNodeInfo2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.PipelineNodeInfoDO;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.mapper.PipelineNodeInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;

/**
 * 流水线节点详情
 * @author mikey
 * @date 2022-04-09 22:04:25
 */
@Slf4j
@Component
public class PipelineNodeInfoRepositoryImpl implements PipelineNodeInfoRepository {

    @Resource
    private PipelineNodeInfoMapper pipelineNodeInfoMapper;
    @Resource
    private PipelineNodeInfo2DOConvector pipelineNodeInfo2DOConvector;

    public void create(PipelineNodeInfo pipelineNodeInfo){
            pipelineNodeInfoMapper.insert(pipelineNodeInfo2DOConvector.toDO(pipelineNodeInfo));
    }

    public void update(PipelineNodeInfo pipelineNodeInfo){
            pipelineNodeInfoMapper.updateById(pipelineNodeInfo2DOConvector.toDO(pipelineNodeInfo));
    }

    public PipelineNodeInfo getById(Long id){
        return pipelineNodeInfo2DOConvector.toDomain(pipelineNodeInfoMapper.selectById(id));
    }

    public PipelineNodeInfo getByNodeId(String nodeId){
        QueryWrapper<PipelineNodeInfoDO> qw = new QueryWrapper<PipelineNodeInfoDO>().eq("node_uuid", nodeId);
        return pipelineNodeInfo2DOConvector.toDomain(pipelineNodeInfoMapper.selectOne(qw));
    }

    @Override
    public List<PipelineNodeInfo> list(PipelineNodeInfoListQry pipelineNodeInfoListQry) {
        return pipelineNodeInfo2DOConvector.toDomainList(pipelineNodeInfoMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<PipelineNodeInfo> page(PipelineNodeInfoPageQry qry) {
        QueryWrapper<PipelineNodeInfoDO> qw = new QueryWrapper<>();
        IPage doPage = pipelineNodeInfoMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),pipelineNodeInfo2DOConvector.toDomainList(doPage.getRecords()));
    }
}
