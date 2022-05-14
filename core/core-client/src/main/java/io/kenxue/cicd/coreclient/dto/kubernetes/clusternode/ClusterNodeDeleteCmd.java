package io.kenxue.cicd.coreclient.dto.kubernetes.clusternode;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * kubernetes集群节点
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@Accessors(chain = true)
public class ClusterNodeDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
