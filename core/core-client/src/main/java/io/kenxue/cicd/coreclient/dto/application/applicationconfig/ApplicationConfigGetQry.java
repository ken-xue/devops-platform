package io.kenxue.cicd.coreclient.dto.application.applicationconfig;

import io.kenxue.cicd.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigGetQry extends CommonCommand {

    private Long id;
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 应用配置
     */
    private String configContext;
}