package io.kenxue.devops.coreclient.api.project;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskworkinghours.*;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskWorkingHoursAppService {
    Response add(TaskWorkingHoursAddCmd cmd);
    Response update(TaskWorkingHoursUpdateCmd cmd);
    Response delete(TaskWorkingHoursDeleteCmd userDeleteCmd);
    SingleResponse<TaskWorkingHoursDTO> getById(TaskWorkingHoursGetQry qry);
    MultiResponse<TaskWorkingHoursDTO> list(TaskWorkingHoursListQry qry);
    PageResponse<TaskWorkingHoursDTO> page(TaskWorkingHoursPageQry userPageQry);
}
