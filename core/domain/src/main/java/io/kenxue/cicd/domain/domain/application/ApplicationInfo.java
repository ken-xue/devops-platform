package io.kenxue.cicd.domain.domain.application;

import io.kenxue.cicd.domain.common.CommonEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationInfo extends CommonEntity {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 项目uuid
     */
    private String projectUuid;
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
