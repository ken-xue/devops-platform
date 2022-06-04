package io.kenxue.devops.coreclient.api.project;

import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.project.taskfile.*;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskFileAppService {
    Response add(TaskFileAddCmd cmd);
    Response update(TaskFileUpdateCmd cmd);
    Response delete(TaskFileDeleteCmd userDeleteCmd);
    SingleResponse<TaskFileDTO> getById(TaskFileGetQry qry);
    MultiResponse<TaskFileDTO> list(TaskFileListQry qry);
    PageResponse<TaskFileDTO> page(TaskFilePageQry userPageQry);
}
