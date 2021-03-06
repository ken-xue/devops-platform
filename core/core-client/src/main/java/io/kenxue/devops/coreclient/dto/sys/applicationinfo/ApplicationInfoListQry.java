package io.kenxue.devops.coreclient.dto.sys.applicationinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 应用
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationInfoListQry extends CommonCommand {
    /**
     * 应用名称
     */
    private String applicationName;
    /**
     * 应用类型
     */
    private String applicationType;
}
