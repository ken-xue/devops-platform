package io.kenxue.devops.coreclient.dto.project.projectinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@Accessors(chain = true)
public class ProjectInfoListQry extends CommonCommand {
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 负责人
     */
    private String userUuid;
    /**
     * 描述
     */
    private String projectDesc;
}
