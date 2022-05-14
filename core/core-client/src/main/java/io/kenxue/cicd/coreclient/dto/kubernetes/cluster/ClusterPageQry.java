package io.kenxue.cicd.coreclient.dto.kubernetes.cluster;

import io.kenxue.cicd.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@Accessors(chain = true)
public class ClusterPageQry extends PageQuery {

    private ClusterDTO clusterDTO;

}
