package io.kenxue.cicd.coreclient.dto.kubernetes.cluster;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@Accessors(chain = true)
public class ClusterCreateCmd extends CommonCommand {
    @NotBlank(message = "请输入集群名字")
    private String name;
    @NotBlank(message = "请选择集群版本")
    private String version;
    @Size(message = "Master机器必选")
    private List<String> masterHostList;
    private List<String> slaveHostList;
}
