package io.kenxue.cicd.application.sys.log.command;

import io.kenxue.cicd.application.sys.log.assembler.Log2DTOAssembler;
import io.kenxue.cicd.domain.domain.sys.Log;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.sys.log.LogAddCmd;
import io.kenxue.cicd.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Component
public class LogAddCmdExe {

    @Resource
    private LogRepository logRepository;
    @Resource
    private Log2DTOAssembler log2DTOAssembler;

    public Response execute(LogAddCmd cmd) {
        Log log = log2DTOAssembler.toDomain(cmd.getLogDTO());
        logRepository.create(log);
        return Response.success();
    }
}
