package io.kenxue.devops.application.project.taskoperatorlogger.command.query;

import io.kenxue.devops.application.project.taskoperatorlogger.assembler.TaskOperatorLogger2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskoperatorlogger.TaskOperatorLoggerGetQry;
import io.kenxue.devops.domain.repository.project.TaskOperatorLoggerRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 项目任务操作日志
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskOperatorLoggerGetQryExe {

    @Resource
    private TaskOperatorLoggerRepository taskOperatorLoggerRepository;
    @Resource
    private TaskOperatorLogger2DTOAssembler taskOperatorLogger2DTOAssembler;

    public SingleResponse<TaskOperatorLoggerDTO> execute(TaskOperatorLoggerGetQry qry) {
        return SingleResponse.of(taskOperatorLogger2DTOAssembler.toDTO(taskOperatorLoggerRepository.getById(qry.getId())));
    }

}
