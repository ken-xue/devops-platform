package io.kenxue.cicd.application.middleware.minio.assembler;

import io.kenxue.cicd.application.common.assembler.Assembler;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.middleware.minio.MinioDTO;
import io.kenxue.cicd.domain.domain.middleware.Minio;
import org.springframework.stereotype.Component;

import java.util.List;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Component
public class Minio2DTOAssembler implements Assembler<MinioDTO, Minio> {

    @Override
    public MinioDTO toDTO(Minio minio) {
        return Minio2DTOMapStruct.INSTANCE.toDTO(minio);
    }

    @Override
    public Minio toDomain(MinioDTO minioDTO) {
        return Minio2DTOMapStruct.INSTANCE.toDomain(minioDTO);
    }

    @Override
    public List<MinioDTO> toDTOList(List<Minio> minioList) {
        return Minio2DTOMapStruct.INSTANCE.toDTOList(minioList);
    }

    @Override
    public List<Minio> toDomainList(List<MinioDTO> minioDTOList) {
        return Minio2DTOMapStruct.INSTANCE.toDomainList(minioDTOList);
    }

    @Override
    public Page<MinioDTO> toDTOPage(Page<Minio> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Minio2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
