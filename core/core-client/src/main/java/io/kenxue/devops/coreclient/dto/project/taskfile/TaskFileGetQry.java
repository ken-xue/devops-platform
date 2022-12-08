package io.kenxue.devops.coreclient.dto.project.taskfile;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskFileGetQry extends CommonCommand {

    private Long id;
    @Schema(description = "任务uuid")
    private String taskUuid;
    @Schema(description = "文件名称")
    private Date fileName;
    @Schema(description = "文件路径")
    private Date url;
}