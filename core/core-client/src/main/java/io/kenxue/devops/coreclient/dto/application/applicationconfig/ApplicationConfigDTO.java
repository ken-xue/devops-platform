package io.kenxue.devops.coreclient.dto.application.applicationconfig;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class ApplicationConfigDTO extends CommonDTO {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 应用配置
     */
    private String configContext;
}
