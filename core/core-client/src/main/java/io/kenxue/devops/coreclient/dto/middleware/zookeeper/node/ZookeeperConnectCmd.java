package io.kenxue.devops.coreclient.dto.middleware.zookeeper.node;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘牌
 * @Title:
 * @date 2022/2/2117:35
 */
@Data
@Accessors(chain = true)
public class ZookeeperConnectCmd {
    private String ip;
    private Integer port;
}
