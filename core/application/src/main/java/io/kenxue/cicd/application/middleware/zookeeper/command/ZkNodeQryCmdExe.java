package io.kenxue.cicd.application.middleware.zookeeper.command;

import io.kenxue.cicd.coreclient.dto.basic.zookeeper.ZkTreeNode;
import io.kenxue.cicd.domain.repository.basic.ZookeeperRepository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2210:27
 */
@Component
public class ZkNodeQryCmdExe {
    @Resource
    private ZookeeperRepository zookeeperRepository;

    public List<ZkTreeNode> execute(String id) {
        return zookeeperRepository.lazyLeaf(id);
    }
}
