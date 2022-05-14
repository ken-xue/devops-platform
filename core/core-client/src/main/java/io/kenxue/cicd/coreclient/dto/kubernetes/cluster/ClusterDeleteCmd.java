package io.kenxue.cicd.coreclient.dto.kubernetes.cluster;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import javax.validation.constraints.NotNull;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@Accessors(chain = true)
public class ClusterDeleteCmd extends CommonCommand {
    @NotNull
    private Long[] ids;
}
