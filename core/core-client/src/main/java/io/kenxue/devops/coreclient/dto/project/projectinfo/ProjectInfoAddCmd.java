package io.kenxue.devops.coreclient.dto.project.projectinfo;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import jakarta.validation.constraints.NotNull;
import lombok.experimental.Accessors;

/**
 * 项目
 * @author mikey
 * @date 2022-02-18 14:06:52
 */
@Data
@Accessors(chain = true)
public class ProjectInfoAddCmd extends CommonCommand {
    @NotNull
    private ProjectInfoDTO projectInfoDTO;
}
