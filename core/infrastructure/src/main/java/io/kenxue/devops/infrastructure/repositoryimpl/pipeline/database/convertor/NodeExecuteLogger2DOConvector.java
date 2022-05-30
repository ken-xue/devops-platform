package io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
import org.springframework.stereotype.Component;
import io.kenxue.devops.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Component
public class NodeExecuteLogger2DOConvector implements Convector<NodeLogger,NodeExecuteLoggerDO>{
    
    public NodeExecuteLoggerDO toDO(NodeLogger nodeLogger) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDO(nodeLogger);
    }

    public NodeLogger toDomain(NodeExecuteLoggerDO nodeExecuteLoggerDO) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDomain(nodeExecuteLoggerDO);
    }

    public List<NodeExecuteLoggerDO> toDOList(List<NodeLogger> nodeLoggerList) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDOList(nodeLoggerList);
    }

    public List<NodeLogger> toDomainList(List<NodeExecuteLoggerDO> nodeExecuteLoggerDOList) {
        return NodeExecuteLogger2DOMapStruct.INSTANCE.toDomainList(nodeExecuteLoggerDOList);
    }
}
