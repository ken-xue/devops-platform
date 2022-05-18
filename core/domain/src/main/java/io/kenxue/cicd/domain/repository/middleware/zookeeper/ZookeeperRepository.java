package io.kenxue.cicd.domain.repository.middleware.zookeeper;

import io.kenxue.cicd.coreclient.dto.common.page.Page;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperListQry;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperPageQry;
import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import java.util.List;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
public interface ZookeeperRepository {
    void create(Zookeeper zookeeper);
    void update(Zookeeper zookeeper);
    Zookeeper getById(Long id);
    List<Zookeeper> list(ZookeeperListQry zookeeperListQry);
    Page<Zookeeper> page(ZookeeperPageQry qry);
}
