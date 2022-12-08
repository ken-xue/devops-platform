package io.kenxue.devops.coreclient.dto.kubernetes.clusternode;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import jakarta.validation.constraints.NotNull;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterNodeDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
