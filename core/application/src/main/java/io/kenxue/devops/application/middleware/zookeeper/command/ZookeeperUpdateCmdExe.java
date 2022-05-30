package io.kenxue.devops.application.middleware.zookeeper.command;

import io.kenxue.devops.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.devops.domain.domain.middleware.Zookeeper;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperUpdateCmd;
import io.kenxue.devops.domain.repository.middleware.zookeeper.ZookeeperRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class ZookeeperUpdateCmdExe {

    @Resource
    private ZookeeperRepository zookeeperRepository;
    @Resource
    private Zookeeper2DTOAssembler zookeeper2DTOAssembler;

    public Response execute(ZookeeperUpdateCmd cmd) {
        Zookeeper zookeeper = zookeeper2DTOAssembler.toDomain(cmd.getZookeeperDTO());
        zookeeperRepository.update(zookeeper);
        return Response.success();
    }
}