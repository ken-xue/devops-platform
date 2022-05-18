package io.kenxue.cicd.application.middleware.zookeeper.command.query;

import io.kenxue.cicd.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.MultiResponse;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperListQry;
import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import io.kenxue.cicd.domain.repository.middleware.zookeeper.ZookeeperRepository;
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
