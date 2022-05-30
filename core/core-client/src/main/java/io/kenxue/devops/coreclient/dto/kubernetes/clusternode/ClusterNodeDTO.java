package io.kenxue.devops.coreclient.dto.kubernetes.clusternode;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.kenxue.devops.sharedataboject.kubernetes.enums.KubernetesNodeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterNodeDTO extends CommonDTO {
    /**
     * 集群uuid
     */
    private String clusterUuid;
    /**
     * 节点名称
     */
    private String name;
    /**
     * 节点类型(主/从节点)
     */
    private KubernetesNodeEnum type;
    /**
     * 机器uuid
     */
    private String machineUuid;
}
