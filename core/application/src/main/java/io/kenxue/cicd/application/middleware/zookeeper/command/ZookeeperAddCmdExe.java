package io.kenxue.cicd.application.middleware.zookeeper.command;

import io.kenxue.cicd.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.middleware.zookeeper.ZookeeperRepository;
import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperAddCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import io.kenxue.cicd.coreclient.context.UserThreadContext;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class ZookeeperAddCmdExe {

    @Resource
    private ZookeeperRepository zookeeperRepository;
    @Resource
    private Zookeeper2DTOAssembler zookeeper2DTOAssembler;

    public Response execute(ZookeeperAddCmd cmd) {
        Zookeeper zookeeper = zookeeper2DTOAssembler.toDomain(cmd.getZookeeperDTO());
        zookeeper.create(UserThreadContext.get());
        zookeeperRepository.create(zookeeper);
        return Response.success();
    }
}
