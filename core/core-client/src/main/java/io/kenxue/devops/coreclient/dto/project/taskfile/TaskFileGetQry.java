package io.kenxue.devops.coreclient.dto.project.taskfile;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskFileGetQry extends CommonCommand {

    private Long id;
    @ApiModelProperty(value = "任务uuid")
    private String taskUuid;
    @ApiModelProperty(value = "文件名称")
    private Date fileName;
    @ApiModelProperty(value = "文件路径")
    private Date url;
}