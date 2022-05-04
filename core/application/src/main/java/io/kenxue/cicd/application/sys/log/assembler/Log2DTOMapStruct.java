package io.kenxue.cicd.application.sys.log.assembler;

import io.kenxue.cicd.coreclient.dto.sys.log.LogDTO;
import io.kenxue.cicd.domain.domain.sys.Log;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 系统日志
 * @author mikey
 * @date 2021-12-03 17:28:52
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Log2DTOMapStruct {

    Log2DTOMapStruct INSTANCE = Mappers.getMapper(Log2DTOMapStruct.class);

    @Mappings({})
    LogDTO toDTO(Log log);

    @Mappings({})
    Log toDomain(LogDTO logDTO);


    List<LogDTO> toDTOList(List<Log> logList);


    List<Log> toDomainList(List<LogDTO> logDTOList);
}
