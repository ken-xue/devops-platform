package io.kenxue.cicd.infrastructure.repositoryimpl.kubernetes.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
import java.util.Date;
/**
 * kubernetes集群
 * @author mikey
 * @date 2022-05-13 23:03:20
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
    /**
     * 集群配置文件
     */
    private String config;
}
