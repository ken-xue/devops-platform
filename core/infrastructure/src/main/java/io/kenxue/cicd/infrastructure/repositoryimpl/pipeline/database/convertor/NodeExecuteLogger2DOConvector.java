package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.repositoryimpl.sys.database.convertor.Convector;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLogger2DOConvector implements Convector<NodeExecuteLogger,NodeExecuteLoggerDO>{
    
    public NodeExecuteLoggerDO toDO(NodeExecuteLogger nodeExecuteLogger) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDO(nodeExecuteLogger);
    }

    public NodeExecuteLogger toDomain(NodeExecuteLoggerDO nodeExecuteLoggerDO) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDomain(nodeExecuteLoggerDO);
    }

    public List<NodeExecuteLoggerDO> toDOList(List<NodeExecuteLogger> nodeExecuteLoggerList) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDOList(nodeExecuteLoggerList);
    }

    public List<NodeExecuteLogger> toDomainList(List<NodeExecuteLoggerDO> nodeExecuteLoggerDOList) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDomainList(nodeExecuteLoggerDOList);
    }
}
