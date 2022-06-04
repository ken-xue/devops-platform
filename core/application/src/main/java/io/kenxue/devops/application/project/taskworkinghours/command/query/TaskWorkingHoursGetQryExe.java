package io.kenxue.devops.application.project.taskworkinghours.command.query;

import io.kenxue.devops.application.project.taskworkinghours.assembler.TaskWorkingHours2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursGetQry;
import io.kenxue.devops.domain.repository.project.TaskWorkingHoursRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHoursGetQryExe {

    @Resource
    private TaskWorkingHoursRepository taskWorkingHoursRepository;
    @Resource
    private TaskWorkingHours2DTOAssembler taskWorkingHours2DTOAssembler;

    public SingleResponse<TaskWorkingHoursDTO> execute(TaskWorkingHoursGetQry qry) {
        return SingleResponse.of(taskWorkingHours2DTOAssembler.toDTO(taskWorkingHoursRepository.getById(qry.getId())));
    }

}
