package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursListQry;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.TaskWorkingHoursPageQry;
import io.kenxue.devops.domain.domain.project.TaskWorkingHours;
import java.util.List;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskWorkingHoursRepository {
    void create(TaskWorkingHours taskWorkingHours);
    void update(TaskWorkingHours taskWorkingHours);
    TaskWorkingHours getById(Long id);
    List<TaskWorkingHours> list(TaskWorkingHoursListQry taskWorkingHoursListQry);
    Page<TaskWorkingHours> page(TaskWorkingHoursPageQry qry);
}
