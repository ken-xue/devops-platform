package io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.convertor;

import io.kenxue.devops.infrastructure.common.convector.Convector;
import io.kenxue.devops.domain.domain.middleware.Minio;
import io.kenxue.devops.infrastructure.repositoryimpl.middleware.database.dataobject.MinioDO;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * minio实例
 * @author 麦奇
 * @date 2022-05-25 23:50:28
 */
@Component
public class Minio2DOConvector implements Convector<Minio,MinioDO>{
    
    public MinioDO toDO(Minio minio) {
        return Minio2DOMapStruct.INSTANCE.toDO(minio);
    }

    public Minio toDomain(MinioDO minioDO) {
        return Minio2DOMapStruct.INSTANCE.toDomain(minioDO);
    }

    public List<MinioDO> toDOList(List<Minio> minioList) {
        return Minio2DOMapStruct.INSTANCE.toDOList(minioList);
    }

    public List<Minio> toDomainList(List<MinioDO> minioDOList) {
        return Minio2DOMapStruct.INSTANCE.toDomainList(minioDOList);
    }
}
