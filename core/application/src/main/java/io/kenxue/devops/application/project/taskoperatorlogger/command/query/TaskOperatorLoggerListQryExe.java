package io.kenxue.devops.application.project.taskoperatorlogger.command.query;

import io.kenxue.devops.application.project.taskoperatorlogger.assembler.TaskOperatorLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerDTO;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerListQry;
import io.kenxue.devops.domain.domain.project.TaskOperatorLogger;
import io.kenxue.devops.domain.repository.project.TaskOperatorLoggerRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOperatorLoggerListQryExe {
    @Resource
    private TaskOperatorLoggerRepository taskOperatorLoggerRepository;
    @Resource
    private TaskOperatorLogger2DTOAssembler taskOperatorLogger2DTOAssembler;

    public MultiResponse<TaskOperatorLoggerDTO> execute(TaskOperatorLoggerListQry qry) {
        List<TaskOperatorLogger> taskOperatorLoggerDTOList = taskOperatorLoggerRepository.list(qry);
        return MultiResponse.of(taskOperatorLogger2DTOAssembler.toDTOList(taskOperatorLoggerDTOList));
    }
}
