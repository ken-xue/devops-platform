package io.kenxue.devops.infrastructure.repositoryimpl.kubernetes.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.devops.domain.common.CommonEntity;
import lombok.Data;

/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-20 14:09:32
 */
@Data
@TableName("kubernetes_cluster")
public class ClusterDO extends CommonEntity {
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

    private String accessUrl;
    /**
     * 集群配置文件
     */
    private String config;

    private String username;

    private String password;

    private String accessWay;

    /**
     * 用于集群访问终端的机器节点
     */
    private String machineUuid;
}
