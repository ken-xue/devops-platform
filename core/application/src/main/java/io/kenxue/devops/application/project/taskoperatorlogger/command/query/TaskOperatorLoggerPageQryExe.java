package io.kenxue.devops.application.project.taskoperatorlogger.command.query;

import io.kenxue.devops.application.project.taskoperatorlogger.assembler.TaskOperatorLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerDTO;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.TaskOperatorLoggerRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOperatorLoggerPageQryExe {

    @Resource
    private TaskOperatorLoggerRepository taskOperatorLoggerRepository;
    @Resource
    private TaskOperatorLogger2DTOAssembler taskOperatorLogger2DTOAssembler;

    public PageResponse<TaskOperatorLoggerDTO> execute(TaskOperatorLoggerPageQry qry) {
        Page<TaskOperatorLogger> page = taskOperatorLoggerRepository.page(qry);
        return PageResponse.of(taskOperatorLogger2DTOAssembler.toDTOPage(page));
    }
}
