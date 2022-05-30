package io.kenxue.devops.application.middleware.zookeeper.command.query;

import io.kenxue.devops.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.devops.coreclient.dto.common.response.MultiResponse;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperListQry;
import io.kenxue.devops.domain.domain.middleware.Zookeeper;
import io.kenxue.devops.domain.repository.middleware.zookeeper.ZookeeperRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class ZookeeperListQryExe {
    @Resource
    private ZookeeperRepository zookeeperRepository;
    @Resource
    private Zookeeper2DTOAssembler zookeeper2DTOAssembler;

    public MultiResponse<ZookeeperDTO> execute(ZookeeperListQry qry) {
        List<Zookeeper> zookeeperDTOList = zookeeperRepository.list(qry);
        return MultiResponse.of(zookeeper2DTOAssembler.toDTOList(zookeeperDTOList));
    }
}
