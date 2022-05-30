package io.kenxue.devops.coreclient.dto.kubernetes.clusternode.event;

import io.kenxue.devops.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@AllArgsConstructor
public class ClusterNodeCreateEvent implements DomainEventI {
    private String uuid;
}
