package io.kenxue.cicd.coreclient.dto.middleware.zookeeper.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 基建中间件zk
 * @author steakliu
 * @date 2022-05-15 16:46:02
 */
@Data
@AllArgsConstructor
public class ZookeeperCreateEvent implements DomainEventI {
    private String uuid;
}
