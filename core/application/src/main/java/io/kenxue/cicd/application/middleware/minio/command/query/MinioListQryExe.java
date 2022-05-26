package io.kenxue.cicd.application.middleware.minio.command.query;

import io.kenxue.cicd.application.middleware.minio.assembler.Minio2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioDTO;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioListQry;
import io.kenxue.cicd.domain.domain.middleware.Minio;
import io.kenxue.cicd.domain.repository.middleware.MinioRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Component
public class MinioListQryExe {
    @Resource
    private MinioRepository minioRepository;
    @Resource
    private Minio2DTOAssembler minio2DTOAssembler;

    public MultiResponse<MinioDTO> execute(MinioListQry qry) {
        List<Minio> minioDTOList = minioRepository.list(qry);
        return MultiResponse.of(minio2DTOAssembler.toDTOList(minioDTOList));
    }
}
