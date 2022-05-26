package io.kenxue.cicd.coreclient.api.middleware;

import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.common.response.SingleResponse;
import io.kenxue.cicd.coreclient.dto.middleware.minio.*;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
public interface MinioAppService {
    Response add(MinioAddCmd cmd);
    Response update(MinioUpdateCmd cmd);
    Response delete(MinioDeleteCmd userDeleteCmd);
    SingleResponse<MinioDTO> getById(MinioGetQry qry);
    MultiResponse<MinioDTO> list(MinioListQry qry);
    PageResponse<MinioDTO> page(MinioPageQry userPageQry);
}
