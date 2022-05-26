package io.kenxue.cicd.application.middleware.minio.command;

import io.kenxue.cicd.coreclient.context.UserThreadContext;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.middleware.MinioRepository;
import io.kenxue.cicd.domain.domain.middleware.Minio;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Component
public class MinioDeleteCmdExe {
    @Resource
    private MinioRepository minioRepository;

    public Response execute(MinioDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Minio minio =minioRepository.getById(id);
            minio.deleted(UserThreadContext.get());
            minioRepository.update(minio);
        }
        return Response.success();
    }
}
