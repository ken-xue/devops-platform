package io.kenxue.devops.application.sys.log.command.query;

import io.kenxue.devops.application.sys.log.assembler.Log2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.sys.log.LogDTO;
import io.kenxue.devops.coreclient.dto.sys.log.LogListQry;
import io.kenxue.devops.domain.domain.sys.Log;
import io.kenxue.devops.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Component
public class LogListQryExe {
    @Resource
    private LogRepository logRepository;
    @Resource
    private Log2DTOAssembler log2DTOAssembler;
    public MultiResponse<LogDTO> execute(LogListQry qry) {
        List<Log> logDTOList = logRepository.list(qry);
        return MultiResponse.of(log2DTOAssembler.toDTOList(logDTOList));
    }
}

