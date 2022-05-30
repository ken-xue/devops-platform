package io.kenxue.devops.coreclient.dto.sys.applicationofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 应用关联用户
 * @author mikey
 * @date 2021-12-26 16:56:03
 */
@Data
@Accessors(chain = true)
public class ApplicationOfUserDTO extends CommonDTO {
    /**
     * 应用uuid
     */
    private String applicationUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
