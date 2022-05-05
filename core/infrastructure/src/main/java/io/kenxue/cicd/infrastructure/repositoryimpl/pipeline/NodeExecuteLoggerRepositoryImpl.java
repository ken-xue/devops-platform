package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerListQry;
import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerPageQry;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import io.kenxue.cicd.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.convertor.NodeExecuteLogger2DOConvector;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.mapper.NodeExecuteLoggerMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import javax.annotation.Resource;
import java.util.List;

/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Slf4j
@Component
public class NodeExecuteLoggerRepositoryImpl implements NodeExecuteLoggerRepository {

    @Resource
    private NodeExecuteLoggerMapper nodeExecuteLoggerMapper;
    @Resource
    private NodeExecuteLogger2DOConvector nodeExecuteLogger2DOConvector;

    public void create(NodeExecuteLogger nodeExecuteLogger){
            nodeExecuteLoggerMapper.insert(nodeExecuteLogger2DOConvector.toDO(nodeExecuteLogger));
    }

    public void update(NodeExecuteLogger nodeExecuteLogger){
            nodeExecuteLoggerMapper.updateById(nodeExecuteLogger2DOConvector.toDO(nodeExecuteLogger));
    }

    public NodeExecuteLogger getById(Long id){
        return nodeExecuteLogger2DOConvector.toDomain(nodeExecuteLoggerMapper.selectById(id));
    }

    @Override
    public List<NodeExecuteLogger> list(NodeExecuteLoggerListQry nodeExecuteLoggerListQry) {
        return nodeExecuteLogger2DOConvector.toDomainList(nodeExecuteLoggerMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<NodeExecuteLogger> page(NodeExecuteLoggerPageQry qry) {
        QueryWrapper<NodeExecuteLoggerDO> qw = new QueryWrapper<>();
        IPage doPage = nodeExecuteLoggerMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),nodeExecuteLogger2DOConvector.toDomainList(doPage.getRecords()));
    }
}
