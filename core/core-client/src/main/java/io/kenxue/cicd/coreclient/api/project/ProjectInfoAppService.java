package io.kenxue.cicd.coreclient.api.project;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.project.projectinfo.*;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
public interface ProjectInfoAppService {
    Response add(ProjectInfoAddCmd cmd);
    Response update(ProjectInfoUpdateCmd cmd);
    Response delete(ProjectInfoDeleteCmd userDeleteCmd);
    SingleResponse<ProjectInfoDTO> getById(ProjectInfoGetQry qry);
    MultiResponse<ProjectInfoDTO> list(ProjectInfoListQry qry);
    PageResponse<ProjectInfoDTO> page(ProjectInfoPageQry userPageQry);
}
