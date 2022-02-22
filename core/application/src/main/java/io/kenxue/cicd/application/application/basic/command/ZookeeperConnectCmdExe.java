package io.kenxue.cicd.application.application.basic.command;

import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkTreeNode;
import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZookeeperConnectCmd;
import io.kenxue.cicd.domain.repository.basic.ZookeeperRepository;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;


/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2117:43
 */
@Component
public class ZookeeperConnectCmdExe {
    @Resource
    private ZookeeperRepository zookeeperRepository;

    public ZkTreeNode execute(ZookeeperConnectCmd zookeeperConnectCmd) {
        return zookeeperRepository.connect(zookeeperConnectCmd);
    }
}
