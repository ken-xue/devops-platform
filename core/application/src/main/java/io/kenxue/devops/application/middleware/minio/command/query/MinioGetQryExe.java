package io.kenxue.devops.application.middleware.minio.command.query;

import io.kenxue.devops.application.middleware.minio.assembler.Minio2DTOAssembler;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioGetQry;
import io.kenxue.devops.domain.repository.middleware.MinioRepository;
import org.springframework.stereotype.Component;
import jakarta.annotation.Resource;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Component
public class MinioGetQryExe {

    @Resource
    private MinioRepository minioRepository;
    @Resource
    private Minio2DTOAssembler minio2DTOAssembler;

    public SingleResponse<MinioDTO> execute(MinioGetQry qry) {
        return SingleResponse.of(minio2DTOAssembler.toDTO(minioRepository.getById(qry.getId())));
    }

}
