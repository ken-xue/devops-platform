package io.kenxue.devops.coreclient.dto.middleware.zookeeper.node;

import lombok.Data;

/**
 * @author 刘牌
 * @Title:
 * @date 2022-05-1713:29
 */
@Data
public class ZookeeperAddOrUpdateNodeDTO {
    private String uuid;
    private String name;
    private String data;
    private Integer type;
}
