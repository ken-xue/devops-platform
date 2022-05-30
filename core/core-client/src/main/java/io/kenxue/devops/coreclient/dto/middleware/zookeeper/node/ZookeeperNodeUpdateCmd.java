package io.kenxue.devops.coreclient.dto.middleware.zookeeper.node;

import lombok.Data;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2216:33
 */
@Data
public class ZookeeperNodeUpdateCmd {
    private ZookeeperAddOrUpdateNodeDTO zookeeperAddOrUpdateNodeDTO;
}
