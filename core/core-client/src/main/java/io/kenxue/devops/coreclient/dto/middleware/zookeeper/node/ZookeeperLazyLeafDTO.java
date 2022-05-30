package io.kenxue.devops.coreclient.dto.middleware.zookeeper.node;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1712:35
 */
@Data
@Accessors(chain = true)
public class ZookeeperLazyLeafDTO {
    private String id;
    private String uuid;
}
