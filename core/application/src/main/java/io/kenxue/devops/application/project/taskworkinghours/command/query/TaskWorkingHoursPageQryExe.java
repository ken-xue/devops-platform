package io.kenxue.devops.application.project.taskworkinghours.command.query;

import io.kenxue.devops.application.project.taskworkinghours.assembler.TaskWorkingHours2DTOAssembler;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursDTO;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.project.TaskWorkingHoursRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import javax.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Component
public class TaskWorkingHoursPageQryExe {

    @Resource
    private TaskWorkingHoursRepository taskWorkingHoursRepository;
    @Resource
    private TaskWorkingHours2DTOAssembler taskWorkingHours2DTOAssembler;

    public PageResponse<TaskWorkingHoursDTO> execute(TaskWorkingHoursPageQry qry) {
        Page<TaskWorkingHours> page = taskWorkingHoursRepository.page(qry);
        return PageResponse.of(taskWorkingHours2DTOAssembler.toDTOPage(page));
    }
}
