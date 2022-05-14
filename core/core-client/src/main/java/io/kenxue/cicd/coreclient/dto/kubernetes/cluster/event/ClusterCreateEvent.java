package io.kenxue.cicd.coreclient.dto.kubernetes.cluster.event;

import io.kenxue.cicd.coreclient.dto.common.event.DomainEventI;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@AllArgsConstructor
public class ClusterCreateEvent implements DomainEventI {
    private String uuid;
}
