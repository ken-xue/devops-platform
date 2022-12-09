package io.kenxue.devops.coreclient.dto.kubernetes.cluster;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kubernetes集群资源
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterResourceQry extends CommonCommand {
    /**
     * 集群ID
     */
    @NotNull
    private Long id;
    /**
     * 命名空间
     */
    private String namespace;
    /**
     * 资源类型
     */
    @NotBlank(message = "资源类型不能为空")
    private String resource;
}
