package io.kenxue.cicd.application.sys.log.command.query;


import io.kenxue.cicd.application.sys.log.assembler.Log2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.sys.log.LogDTO;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.sys.log.LogGetQry;
import io.kenxue.cicd.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
