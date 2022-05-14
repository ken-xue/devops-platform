package io.kenxue.cicd.coreclient.dto.kubernetes.cluster;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@Accessors(chain = true)
public class ClusterDTO extends CommonDTO {
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
    /**
     * 流水线快照
     */
    private String graphContent;
}
