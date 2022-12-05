package io.kenxue.devops.coreclient.dto.kubernetes.cluster;


import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@Builder
@Accessors(chain = true)
public class ClusterImportCmd extends CommonCommand {
    ClusterDTO info;
}
