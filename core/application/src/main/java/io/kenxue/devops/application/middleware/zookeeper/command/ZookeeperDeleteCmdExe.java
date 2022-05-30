package io.kenxue.devops.application.middleware.zookeeper.command;

import io.kenxue.devops.coreclient.context.UserThreadContext;
import io.kenxue.devops.coreclient.dto.common.response.Response;
import io.kenxue.devops.domain.repository.middleware.zookeeper.ZookeeperRepository;
import io.kenxue.devops.domain.domain.middleware.Zookeeper;
import io.kenxue.devops.coreclient.dto.middleware.zookeeper.ZookeeperDeleteCmd;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Component
public class ZookeeperDeleteCmdExe {
    @Resource
    private ZookeeperRepository zookeeperRepository;

    public Response execute(ZookeeperDeleteCmd cmd) {
        for(Long id:cmd.getIds()) {
            Zookeeper zookeeper =zookeeperRepository.getById(id);
            zookeeper.deleted(UserThreadContext.get());
            zookeeperRepository.update(zookeeper);
        }
        return Response.success();
    }
}
