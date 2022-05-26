package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.database.convertor;

import io.kenxue.cicd.domain.domain.middleware.Minio;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.database.dataobject.MinioDO;
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
public interface Minio2DOMapStruct {

    Minio2DOMapStruct INSTANCE = Mappers.getMapper(Minio2DOMapStruct.class);

    @Mappings({})
    MinioDO toDO(Minio minio);

    @Mappings({})
    Minio toDomain(MinioDO minioDO);

    List<MinioDO> toDOList(List<Minio> minioList);

    List<Minio> toDomainList(List<MinioDO> minioDOList);
}
