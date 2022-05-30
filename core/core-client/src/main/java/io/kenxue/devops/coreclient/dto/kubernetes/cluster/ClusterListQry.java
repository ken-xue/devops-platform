package io.kenxue.devops.coreclient.dto.kubernetes.cluster;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@Accessors(chain = true)
public class ClusterListQry extends CommonCommand {
    /**
     * 集群名称
     */
    private String name;
    /**
     * 版本
     */
    private String version;
    /**
     * 秘钥
     */
    private String secretKey;
    /**
     * 集群配置文件
     */
    private String config;
}
