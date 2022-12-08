package io.kenxue.devops.application.project.taskoperatorlogger.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.project.TaskOperatorLoggerRepository;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOperatorLoggerDeleteCmdExe {
    @Resource
    private TaskOperatorLoggerRepository taskOperatorLoggerRepository;

    public Response execute(TaskOperatorLoggerDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            TaskOperatorLogger taskOperatorLogger =taskOperatorLoggerRepository.getById(id);
            taskOperatorLogger.deleted(UserThreadContext.get());
            taskOperatorLoggerRepository.update(taskOperatorLogger);
        }
        return Response.success();
    }
}
