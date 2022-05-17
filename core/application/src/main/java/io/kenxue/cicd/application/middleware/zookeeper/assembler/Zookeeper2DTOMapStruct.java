package io.kenxue.cicd.application.middleware.zookeeper.assembler;

import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
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
public interface Zookeeper2DTOMapStruct {

    Zookeeper2DTOMapStruct INSTANCE = Mappers.getMapper(Zookeeper2DTOMapStruct.class);

    @Mappings({})
    ZookeeperDTO toDTO(Zookeeper zookeeper);

    @Mappings({})
    Zookeeper toDomain(ZookeeperDTO zookeeperDTO);

    List<ZookeeperDTO> toDTOList(List<Zookeeper> ZookeeperList);

    List<Zookeeper> toDomainList(List<ZookeeperDTO> ZookeeperDTOList);
}
