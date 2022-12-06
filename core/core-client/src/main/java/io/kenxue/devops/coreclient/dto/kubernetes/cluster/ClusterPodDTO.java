package io.kenxue.devops.coreclient.dto.kubernetes.cluster;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import io.kenxue.devops.coreclient.dto.kubernetes.clusternode.ClusterNodeDTO;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Builder
@Accessors(chain = true)
public class ClusterPodDTO extends CommonDTO {
    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    private Date createTime;

    private String nodeName;

    private String hostIP;

    private String phase;

    private String podIP;

    private Long clusterId;

    private String namespace;

    private List<String> podIPs;
}
