package io.kenxue.devops.infrastructure.repositoryimpl.pipeline;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.kenxue.devops.acl.obs.ObjectStorageService;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerListQry;
import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerPageQry;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.domain.repository.pipeline.NodeExecuteLoggerRepository;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.convertor.NodeExecuteLogger2DOConvector;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.mapper.NodeExecuteLoggerMapper;
import io.kenxue.devops.sharedataboject.common.obs.BucketEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import jakarta.annotation.Resource;
import java.util.List;
import java.util.Optional;

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
    @Resource
    private ObjectStorageService objectStorageService;

    public void create(NodeLogger nodeLogger){
        //保存正文到obs
        objectStorageService.setString(BucketEnum.PIPELINE_NODE_LOGGER.getName(), nodeLogger.getUuid(),nodeLogger.getLogger());
        //保存到数据库
        nodeExecuteLoggerMapper.insert(nodeExecuteLogger2DOConvector.toDO(nodeLogger));
    }

    public void update(NodeLogger nodeLogger){
        nodeExecuteLoggerMapper.updateById(nodeExecuteLogger2DOConvector.toDO(nodeLogger));
    }

    public NodeLogger getById(Long id){
        NodeLogger logger = nodeExecuteLogger2DOConvector.toDomain(nodeExecuteLoggerMapper.selectById(id));
        Optional.ofNullable(logger).map(v->{
            String content = objectStorageService.getString(BucketEnum.PIPELINE_NODE_LOGGER.getName(), v.getUuid());
            v.setLogger(content);
            return null;
        });
        return logger;
    }

    @Override
    public List<NodeLogger> list(NodeExecuteLoggerListQry nodeExecuteLoggerListQry) {
        return nodeExecuteLogger2DOConvector.toDomainList(nodeExecuteLoggerMapper.selectList(new QueryWrapper<>()));
    }

    @Override
    public Page<NodeLogger> page(NodeExecuteLoggerPageQry qry) {
        QueryWrapper<NodeExecuteLoggerDO> qw = new QueryWrapper<>();
        IPage doPage = nodeExecuteLoggerMapper.selectPage(new PageDTO(qry.getPageIndex(), qry.getPageSize()), qw);
        return Page.of(doPage.getCurrent(),doPage.getSize(),doPage.getTotal(),nodeExecuteLogger2DOConvector.toDomainList(doPage.getRecords()));
    }

    @Override
    public NodeLogger getByLoggerUUIDAndNodeUUID(String loggerUUID, String nodeUUID) {
        QueryWrapper<NodeExecuteLoggerDO> qw = new QueryWrapper<>();
        qw.eq("logger_uuid",loggerUUID).eq("node_uuid",nodeUUID);
        NodeLogger logger = nodeExecuteLogger2DOConvector.toDomain(nodeExecuteLoggerMapper.selectOne(qw));
        Optional.ofNullable(logger).map(v->{
            String content = objectStorageService.getString(BucketEnum.PIPELINE_NODE_LOGGER.getName(), v.getUuid());
            v.setLogger(content);
            return v;
        });
        return logger;
    }
}
