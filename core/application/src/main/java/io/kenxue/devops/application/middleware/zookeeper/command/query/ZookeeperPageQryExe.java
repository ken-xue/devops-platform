package io.kenxue.devops.application.middleware.zookeeper.command.query;

import io.kenxue.devops.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperPageQry;
import io.kenxue.devops.coreclient.dto.common.response.PageResponse;
import io.kenxue.devops.domain.repository.middleware.zookeeper.ZookeeperRepository;
import org.springframework.stereotype.Component;
import io.kenxue.devops.domain.domain.middleware.Zookeeper;
import jakarta.annotation.Resource;
import io.kenxue.devops.coreclient.dto.common.page.Page;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class ZookeeperPageQryExe {

    @Resource
    private ZookeeperRepository zookeeperRepository;
    @Resource
    private Zookeeper2DTOAssembler zookeeper2DTOAssembler;

    public PageResponse<ZookeeperDTO> execute(ZookeeperPageQry qry) {
        Page<Zookeeper> page = zookeeperRepository.page(qry);
        return PageResponse.of(zookeeper2DTOAssembler.toDTOPage(page));
    }
}
