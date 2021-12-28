package io.kenxue.cicd.coreclient.dto.application.applicationinfo;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 应用
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationInfoDTO extends CommonDTO {
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
