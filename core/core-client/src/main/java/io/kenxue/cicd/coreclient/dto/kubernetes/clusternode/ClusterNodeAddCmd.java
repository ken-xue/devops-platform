package io.kenxue.cicd.coreclient.dto.kubernetes.clusternode;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterNodeAddCmd extends CommonCommand {
    @NotNull
    private ClusterNodeDTO clusterNodeDTO;
}
