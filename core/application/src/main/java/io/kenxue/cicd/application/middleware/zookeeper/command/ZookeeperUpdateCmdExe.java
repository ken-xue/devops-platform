package io.kenxue.cicd.application.middleware.zookeeper.command;

import io.kenxue.cicd.application.middleware.zookeeper.assembler.Zookeeper2DTOAssembler;
import io.kenxue.cicd.domain.domain.middleware.Zookeeper;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.coreclient.dto.middleware.zookeeper.ZookeeperUpdateCmd;
import io.kenxue.cicd.domain.repository.middleware.zookeeper.ZookeeperRepository;
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