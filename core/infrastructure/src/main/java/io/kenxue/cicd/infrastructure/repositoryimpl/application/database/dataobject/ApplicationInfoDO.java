package io.kenxue.cicd.infrastructure.repositoryimpl.application.database.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.Data;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@TableName("application_info")
public class ApplicationInfoDO extends CommonEntity {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用类型
     */
    private String applicationType;
    /**
     * 代码仓库
     */
    private String repository;
    /**
     * 访问密钥
     */
    private String accessKey;
    /**
     * 访问方式
     */
    private String accessWay;
}
