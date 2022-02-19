package io.kenxue.cicd.coreclient.dto.project.projectinfo;

import io.kenxue.cicd.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@Accessors(chain = true)
public class ProjectInfoDTO extends CommonDTO {
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 负责人
     */
    private String userUuid;
    /**
     * 负责人username
     */
    private String username;
    /**
     * 描述
     */
    private String projectDesc;
}
