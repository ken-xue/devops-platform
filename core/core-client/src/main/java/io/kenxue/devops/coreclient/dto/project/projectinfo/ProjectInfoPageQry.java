package io.kenxue.devops.coreclient.dto.project.projectinfo;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import io.kenxue.devops.sharedataboject.project.enums.ProjectTypeEnum;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@Accessors(chain = true)
public class ProjectInfoPageQry extends PageQuery {

    private ProjectInfoDTO projectInfoDTO;

    private ProjectTypeEnum projectType;

}
