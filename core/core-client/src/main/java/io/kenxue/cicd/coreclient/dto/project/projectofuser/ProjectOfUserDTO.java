package io.kenxue.cicd.coreclient.dto.project.projectofuser;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 项目关联用户
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@Accessors(chain = true)
public class ProjectOfUserDTO extends CommonDTO {
    /**
     * 项目uuid
     */
    private String projectUuid;
    /**
     * 用户uuid
     */
    private String userUuid;
}
