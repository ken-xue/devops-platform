package io.kenxue.devops.application.middleware.minio.assembler;

import io.kenxue.devops.coreclient.dto.middleware.minio.MinioDTO;
import io.kenxue.devops.domain.domain.middleware.Minio;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Minio2DTOMapStruct {

    Minio2DTOMapStruct INSTANCE = Mappers.getMapper(Minio2DTOMapStruct.class);

    @Mappings({})
    MinioDTO toDTO(Minio minio);

    @Mappings({})
    Minio toDomain(MinioDTO minioDTO);

    List<MinioDTO> toDTOList(List<Minio> MinioList);

    List<Minio> toDomainList(List<MinioDTO> MinioDTOList);
}
