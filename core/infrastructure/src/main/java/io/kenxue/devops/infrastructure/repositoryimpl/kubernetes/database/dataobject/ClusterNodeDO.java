package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@TableName("kubernetes_cluster_node")
public class ClusterNodeDO extends CommonEntity {
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
