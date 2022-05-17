package io.kenxue.cicd.application.middleware.zookeeper.command.query;

import io.kenxue.cicd.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperDTO;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperPageQry;
import io.kenxue.cicd.coreclient.dto.common.response.PageResponse;
import io.kenxue.cicd.domain.repository.middleware.ZookeeperRepository;
import org.springframework.stereotype.Component;
import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.dto.common.page.Page;
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
