package io.kenxue.devops.coreclient.dto.kubernetes.cluster;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterPageQry extends PageQuery {

    private ClusterDTO clusterDTO;

}
