package io.kenxue.cicd.application.middleware.minio.command.query;

import io.kenxue.cicd.application.middleware.minio.assembler.Minio2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioDTO;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.middleware.MinioRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.middleware.Minio;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
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
