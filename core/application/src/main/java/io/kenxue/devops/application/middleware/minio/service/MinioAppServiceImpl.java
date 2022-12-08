package io.kenxue.devops.application.middleware.minio.service;

import io.kenxue.devops.coreclient.api.middleware.MinioAppService;
import io.kenxue.devops.coreclient.dto.middleware.minio.*;
import io.kenxue.devops.application.middleware.minio.command.MinioAddCmdExe;
import io.kenxue.devops.application.middleware.minio.command.MinioDeleteCmdExe;
import io.kenxue.devops.application.middleware.minio.command.MinioUpdateCmdExe;
import io.kenxue.devops.application.middleware.minio.command.query.MinioGetQryExe;
import io.kenxue.devops.application.middleware.minio.command.query.MinioListQryExe;
import io.kenxue.devops.application.middleware.minio.command.query.MinioPageQryExe;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;

/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Service
public class MinioAppServiceImpl implements MinioAppService {
    @Resource
    private MinioAddCmdExe minioAddCmdExe;
    @Resource
    private MinioUpdateCmdExe minioUpdateCmdExe;
    @Resource
    private MinioGetQryExe minioGetQryExe;
    @Resource
    private MinioListQryExe minioListQryExe;
    @Resource
    private MinioPageQryExe minioPageQryExe;
    @Resource
    private MinioDeleteCmdExe minioDeleteCmdExe;

    @Override
    public Response add(MinioAddCmd minioAddCmd) {
        return  minioAddCmdExe.execute(minioAddCmd);
    }

    @Override
    public Response update(MinioUpdateCmd cmd) {
        return minioUpdateCmdExe.execute(cmd);
    }

    @Override
    public SingleResponse<MinioDTO> getById(MinioGetQry qry) {
        return minioGetQryExe.execute(qry);
    }

    @Override
    public MultiResponse<MinioDTO> list(MinioListQry qry) {
        return minioListQryExe.execute(qry);
    }

    @Override
    public Response delete(MinioDeleteCmd minioDeleteCmd) {
        return minioDeleteCmdExe.execute(minioDeleteCmd);
    }

    @Override
    public PageResponse<MinioDTO> page(MinioPageQry minioPageQry) {
        return minioPageQryExe.execute(minioPageQry);
    }
}
