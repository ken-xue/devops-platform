package io.kenxue.devops.application.pipeline.logger.node.assembler;

import io.kenxue.devops.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.devops.domain.domain.pipeline.NodeLogger;
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
public interface NodeExecuteLogger2DTOMapStruct {

    NodeExecuteLogger2DTOMapStruct INSTANCE = Mappers.getMapper(NodeExecuteLogger2DTOMapStruct.class);

    @Mappings({})
    NodeExecuteLoggerDTO toDTO(NodeLogger nodeLogger);

    @Mappings({})
    NodeLogger toDomain(NodeExecuteLoggerDTO nodeExecuteLoggerDTO);

    List<NodeExecuteLoggerDTO> toDTOList(List<NodeLogger> nodeLoggerList);

    List<NodeLogger> toDomainList(List<NodeExecuteLoggerDTO> NodeExecuteLoggerDTOList);
}
