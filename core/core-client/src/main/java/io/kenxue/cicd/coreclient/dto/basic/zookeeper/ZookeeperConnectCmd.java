package io.kenxue.cicd.coreclient.dto.basic.zookeeper;

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
