package io.kenxue.devops.application.middleware.minio.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.middleware.MinioRepository;
import io.kenxue.devops.domain.domain.middleware.Minio;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioDeleteCmd;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
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
