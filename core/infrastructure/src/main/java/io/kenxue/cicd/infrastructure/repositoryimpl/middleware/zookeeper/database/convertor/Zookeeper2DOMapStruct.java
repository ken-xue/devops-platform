package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.database.convertor;

import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.database.dataobject.ZookeeperDO;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import java.util.List;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface Zookeeper2DOMapStruct {

    Zookeeper2DOMapStruct INSTANCE = Mappers.getMapper(Zookeeper2DOMapStruct.class);

    @Mappings({})
    ZookeeperDO toDO(Zookeeper zookeeper);

    @Mappings({})
    Zookeeper toDomain(ZookeeperDO zookeeperDO);

    List<ZookeeperDO> toDOList(List<Zookeeper> zookeeperList);

    List<Zookeeper> toDomainList(List<ZookeeperDO> zookeeperDOList);
}
