package io.kenxue.cicd.application.middleware.zookeeper.command;

import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkNodeAddCmd;
import io.kenxue.cicd.coreclient.dto.common.response.Response;
import io.kenxue.cicd.domain.repository.middleware.ZookeeperRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2216:35
 */
@Component
public class ZkNodeAddCmdExe {
    @Resource
    private ZookeeperRepository zookeeperRepository;

    public Response execute(ZkNodeAddCmd nodeAddCmd) {
        return zookeeperRepository.addZkNode(nodeAddCmd);
    }
}
