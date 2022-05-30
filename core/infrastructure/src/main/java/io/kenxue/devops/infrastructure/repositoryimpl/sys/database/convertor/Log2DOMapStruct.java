package io.kenxue.devops.infrastructure.repositoryimpl.sys.database.convertor;

import io.kenxue.devops.domain.domain.sys.Log;
import io.kenxue.devops.infrastructure.repositoryimpl.sys.database.dataobject.LogDO;
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
public interface Log2DOMapStruct {
    Log2DOMapStruct INSTANCE = Mappers.getMapper(Log2DOMapStruct.class);
    @Mappings({})
    LogDO toDO(Log log);
    @Mappings({})
    Log toDomain(LogDO logDO);
    List<LogDO> toDOList(List<Log> logList);
    List<Log> toDomainList(List<LogDO> logDOList);
}
