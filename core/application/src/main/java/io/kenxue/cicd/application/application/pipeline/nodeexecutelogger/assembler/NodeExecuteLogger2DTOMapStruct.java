package io.kenxue.cicd.application.application.pipeline.nodeexecutelogger.assembler;

import io.kenxue.cicd.coreclient.dto.pipeline.nodeexecutelogger.NodeExecuteLoggerDTO;
import io.kenxue.cicd.domain.domain.pipeline.NodeExecuteLogger;
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
    NodeExecuteLoggerDTO toDTO(NodeExecuteLogger nodeExecuteLogger);

    @Mappings({})
    NodeExecuteLogger toDomain(NodeExecuteLoggerDTO nodeExecuteLoggerDTO);

    List<NodeExecuteLoggerDTO> toDTOList(List<NodeExecuteLogger> NodeExecuteLoggerList);

    List<NodeExecuteLogger> toDomainList(List<NodeExecuteLoggerDTO> NodeExecuteLoggerDTOList);
}
