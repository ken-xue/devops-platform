package io.kenxue.devops.application.project.taskworkinghours.command.query;

import io.kenxue.devops.application.project.taskworkinghours.assembler.TaskWorkingHours2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursDTO;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursListQry;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import io.kenxue.devops.domain.repository.project.TaskWorkingHoursRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
import java.util.List;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHoursListQryExe {
    @Resource
    private TaskWorkingHoursRepository taskWorkingHoursRepository;
    @Resource
    private TaskWorkingHours2DTOAssembler taskWorkingHours2DTOAssembler;

    public MultiResponse<TaskWorkingHoursDTO> execute(TaskWorkingHoursListQry qry) {
        List<TaskWorkingHours> taskWorkingHoursDTOList = taskWorkingHoursRepository.list(qry);
        return MultiResponse.of(taskWorkingHours2DTOAssembler.toDTOList(taskWorkingHoursDTOList));
    }
}
