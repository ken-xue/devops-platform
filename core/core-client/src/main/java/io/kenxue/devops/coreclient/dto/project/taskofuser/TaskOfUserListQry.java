package io.kenxue.devops.coreclient.dto.project.taskofuser;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOfUserListQry extends CommonCommand {
    @Schema(description = "项目uuid")
    private String taskUuid;
    @Schema(description = "用户uuid")
    private String userUuid;
}
