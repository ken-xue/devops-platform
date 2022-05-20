package io.kenxue.cicd.coreclient.dto.kubernetes.cluster;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterUpdateCmd extends CommonCommand {

    @NotNull
    private ClusterDTO clusterDTO;
}
