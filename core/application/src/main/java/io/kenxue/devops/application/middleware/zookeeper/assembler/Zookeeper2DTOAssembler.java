package io.kenxue.devops.application.middleware.zookeeper.assembler;

import io.kenxue.devops.application.common.assembler.Assembler;
import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.devops.domain.domain.middleware.Zookeeper;
import org.springframework.stereotype.Component;
import java.util.List;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class Zookeeper2DTOAssembler implements Assembler<ZookeeperDTO, Zookeeper> {

    @Override
    public ZookeeperDTO toDTO(Zookeeper zookeeper) {
        return Zookeeper2DTOMapStruct.INSTANCE.toDTO(zookeeper);
    }

    @Override
    public Zookeeper toDomain(ZookeeperDTO zookeeperDTO) {
        return Zookeeper2DTOMapStruct.INSTANCE.toDomain(zookeeperDTO);
    }

    @Override
    public List<ZookeeperDTO> toDTOList(List<Zookeeper> zookeeperList) {
        return Zookeeper2DTOMapStruct.INSTANCE.toDTOList(zookeeperList);
    }

    @Override
    public List<Zookeeper> toDomainList(List<ZookeeperDTO> zookeeperDTOList) {
        return Zookeeper2DTOMapStruct.INSTANCE.toDomainList(zookeeperDTOList);
    }

    @Override
    public Page<ZookeeperDTO> toDTOPage(Page<Zookeeper> page){
        return new Page<>(page.getCurrent(), page.getSize(), page.getTotal(), Zookeeper2DTOMapStruct.INSTANCE.toDTOList(page.getRecords()));
    }
}
