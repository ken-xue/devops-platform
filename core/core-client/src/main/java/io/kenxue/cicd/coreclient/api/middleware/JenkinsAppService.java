package io.kenxue.cicd.coreclient.api.middleware;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.middleware.jenkins.*;

/**
 * 基建中间件Jenkins
 * @author mikey
 * @date 2022-05-04 23:25:25
 */
public interface JenkinsAppService {
    Response add(JenkinsAddCmd cmd);
    Response update(JenkinsUpdateCmd cmd);
    Response delete(JenkinsDeleteCmd userDeleteCmd);
    SingleResponse<JenkinsDTO> getById(JenkinsGetQry qry);
    MultiResponse<JenkinsDTO> list(JenkinsListQry qry);
    PageResponse<JenkinsDTO> page(JenkinsPageQry userPageQry);
}
