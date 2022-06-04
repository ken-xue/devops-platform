package io.kenxue.devops.coreclient.dto.project.task;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Data
@Accessors(chain = true)
public class TaskDTO extends CommonDTO {
    @ApiModelProperty(value = "状态")
    private String status;
    @ApiModelProperty(value = "任务开始时间")
    private Date startTime;
    @ApiModelProperty(value = "任务组uuid")
    private String groupUuid;
    @ApiModelProperty(value = "执行人")
    private String userUuid;
    @ApiModelProperty(value = "任务名称")
    private String taskName;
    @ApiModelProperty(value = "任务结束时间")
    private Date endTime;
    @ApiModelProperty(value = "任务详细内容（富文本）")
    private String taskDesc;
    @ApiModelProperty(value = "父级任务uuid")
    private String parentUuid;
    @ApiModelProperty(value = "优先级")
    private String priority;
    @ApiModelProperty(value = "标签")
    private String label;
    @ApiModelProperty(value = "预计工时")
    private Integer taskTime;
    @ApiModelProperty(value = "子任务")
    private List<TaskDTO> children;
}
