package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
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
     * 流水线快照
     */
    private String graphContent;
}
