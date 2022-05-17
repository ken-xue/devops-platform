package io.kenxue.cicd.coreclient.dto.middleware.zookeeper.node;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1716:02
 */
@Data
@Accessors(chain = true)
public class ZookeeperDetailInfoQry {
    private String name;
    private String uuid;
}
