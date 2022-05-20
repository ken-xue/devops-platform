package io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.database.convertor;

import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import io.kenxue.cicd.infrastructure.repositoryimpl.middleware.zookeeper.database.dataobject.ZookeeperDO;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.infrastructure.common.convector.Convector;
import java.util.List;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class Zookeeper2DOConvector implements Convector<Zookeeper,ZookeeperDO>{
    
    public ZookeeperDO toDO(Zookeeper zookeeper) {
        return Zookeeper2DOMapStruct.INSTANCE.toDO(zookeeper);
    }

    public Zookeeper toDomain(ZookeeperDO zookeeperDO) {
        return Zookeeper2DOMapStruct.INSTANCE.toDomain(zookeeperDO);
    }

    public List<ZookeeperDO> toDOList(List<Zookeeper> zookeeperList) {
        return Zookeeper2DOMapStruct.INSTANCE.toDOList(zookeeperList);
    }

    public List<Zookeeper> toDomainList(List<ZookeeperDO> zookeeperDOList) {
        return Zookeeper2DOMapStruct.INSTANCE.toDomainList(zookeeperDOList);
    }
}
