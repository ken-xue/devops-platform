package io.kenxue.cicd.domain.domain.kubernetes;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
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
     * 流水线快照
     */
    private String graphContent;
}
