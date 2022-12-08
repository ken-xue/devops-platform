package io.kenxue.devops.coreclient.dto.kubernetes.cluster;

import lombok.Data;
import lombok.experimental.Accessors;

import jakarta.validation.constraints.NotNull;

/**
 * pod desc
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterPodDescribeQry {
    /**
     * 集群ID
     */
    @NotNull(message = "集群Id不能为空")
    private Long clusterId;
    /**
     * 命名空间
     */
    private String namespace;

    /**
     * pod name
     */
    @NotNull
    private String name;
}
