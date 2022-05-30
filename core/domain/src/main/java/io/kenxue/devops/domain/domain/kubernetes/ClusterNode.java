package io.kenxue.devops.domain.domain.kubernetes;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClusterNode extends CommonEntity {
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
    private String type;
    /**
     * 机器uuid
     */
    private String machineUuid;
}
