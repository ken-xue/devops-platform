package io.kenxue.cicd.coreclient.dto.kubernetes.clusternode;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
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
    private String type;
    /**
     * 流水线快照
     */
    private String graphContent;
}
