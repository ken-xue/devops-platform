package io.kenxue.cicd.coreclient.dto.kubernetes.clusternode.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@AllArgsConstructor
public class ClusterNodeDeleteEvent implements DomainEventI {
    private String uuid;
}
