package io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.convertor;

import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
import io.kenxue.cicd.infrastructure.repositoryimpl.pipeline.database.dataobject.NodeExecuteLoggerDO;
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
    NodeExecuteLoggerDO toDO(NodeExecuteLogger nodeExecuteLogger);

    @Mappings({})
    NodeExecuteLogger toDomain(NodeExecuteLoggerDO nodeExecuteLoggerDO);

    List<NodeExecuteLoggerDO> toDOList(List<NodeExecuteLogger> nodeExecuteLoggerList);

    List<NodeExecuteLogger> toDomainList(List<NodeExecuteLoggerDO> nodeExecuteLoggerDOList);
}
