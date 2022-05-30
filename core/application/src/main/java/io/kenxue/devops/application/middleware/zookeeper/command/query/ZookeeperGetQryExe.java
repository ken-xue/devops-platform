package io.kenxue.devops.application.middleware.zookeeper.command.query;

import io.kenxue.devops.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.devops.coreclient.dto.common.response.SingleResponse;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperGetQry;
import io.kenxue.devops.domain.repository.middleware.zookeeper.ZookeeperRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class ZookeeperGetQryExe {

    @Resource
    private ZookeeperRepository zookeeperRepository;
    @Resource
    private Zookeeper2DTOAssembler zookeeper2DTOAssembler;

    public SingleResponse<ZookeeperDTO> execute(ZookeeperGetQry qry) {
        return SingleResponse.of(zookeeper2DTOAssembler.toDTO(zookeeperRepository.getById(qry.getId())));
    }

}
