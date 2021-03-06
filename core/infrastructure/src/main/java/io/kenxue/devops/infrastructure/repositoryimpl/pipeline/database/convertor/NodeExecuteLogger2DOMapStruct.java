package io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
import io.kenxue.devops.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 节点执行记录
 * @author mikey
 * @date 2022-05-05 11:24:39
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface NodeExecuteLogger2DOMapStruct {

    NodeExecuteLogger2DOMapStruct INSTANCE = Mappers.getMapper(NodeExecuteLogger2DOMapStruct.class);

    @Mappings({})
    NodeExecuteLoggerDO toDO(NodeLogger nodeLogger);

    @Mappings({})
    NodeLogger toDomain(NodeExecuteLoggerDO nodeExecuteLoggerDO);

    List<NodeExecuteLoggerDO> toDOList(List<NodeLogger> nodeLoggerList);

    List<NodeLogger> toDomainList(List<NodeExecuteLoggerDO> nodeExecuteLoggerDOList);
}
