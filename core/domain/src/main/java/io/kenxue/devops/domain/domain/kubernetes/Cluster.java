package io.kenxue.devops.domain.domain.kubernetes;

import io.kenxue.devops.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cluster extends CommonEntity {
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
