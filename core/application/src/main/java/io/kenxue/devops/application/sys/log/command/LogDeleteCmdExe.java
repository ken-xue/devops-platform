package io.kenxue.devops.application.sys.log.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.domain.domain.sys.Log;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.sys.log.LogDeleteCmd;
import io.kenxue.devops.domain.repository.sys.LogRepository;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
/**
 * 系统日志
 * @author mikey
 * @date 2021-11-20 23:04:11
 */
@Component
public class LogDeleteCmdExe {
    @Resource
    private LogRepository logRepository;

    public Response execute(LogDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Log log = logRepository.getById(id);
            log.deleted(UserThreadContext.get());
            logRepository.update(log);
        }
        return Response.success();
    }
}
