package io.kenxue.devops.application.sys.log.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.sys.log.LogDTO;
import io.kenxue.devops.domain.domain.sys.Log;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 系统日志
 * @author mikey
 * @date 2021-12-03 17:28:52
 */
@Component
public class Log2DTOAssembler implements Assembler<LogDTO, Log> {

    public LogDTO toDTO(Log log) {
        return Log2DTOMapStruct.INSTANCE.toDTO(log);
    }

    public Log toDomain(LogDTO logDTO) {
        return Log2DTOMapStruct.INSTANCE.toDomain(logDTO);
    }

    public List<LogDTO> toDTOList(List<Log> logList) {
        return Log2DTOMapStruct.INSTANCE.toDTOList(logList);
    }

    public List<Log> toDomainList(List<LogDTO> logDTOList) {
        return Log2DTOMapStruct.INSTANCE.toDomainList(logDTOList);
    }

    public Page<LogDTO> toDTOPage(Page<Log> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Log2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
