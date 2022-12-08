package io.kenxue.devops.application.middleware.minio.command.query;

import io.kenxue.devops.application.middleware.minio.assembler.Minio2DTOAssembler;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioDTO;
import io.kenxue.devops.coreclient.dto.middleware.minio.MinioPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.middleware.MinioRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.middleware.Minio;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Component
public class MinioPageQryExe {

    @Resource
    private MinioRepository minioRepository;
    @Resource
    private Minio2DTOAssembler minio2DTOAssembler;

    public PageResponse<MinioDTO> execute(MinioPageQry qry) {
        Page<Minio> page = minioRepository.page(qry);
        return PageResponse.of(minio2DTOAssembler.toDTOPage(page));
    }
}
