package io.kenxue.cicd.coreclient.dto.sys.applicationinfo;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationInfoDTO extends CommonDTO {
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
}
