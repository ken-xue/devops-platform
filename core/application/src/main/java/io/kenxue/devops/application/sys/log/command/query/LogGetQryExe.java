package io.kenxue.devops.application.sys.log.command.query;


import io.kenxue.devops.application.sys.log.assembler.Log2DTOAssembler;
import io.kenxue.devops.coreclient.dto.sys.log.LogDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.sys.log.LogGetQry;
import io.kenxue.devops.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Component
public class LogGetQryExe {

    @Resource
    private LogRepository logRepository;
    @Resource
    private Log2DTOAssembler log2DTOAssembler;

    public SingleResponse<LogDTO> execute(LogGetQry qry) {
        return SingleResponse.of(log2DTOAssembler.toDTO(logRepository.getById(qry.getId())));
    }

}
