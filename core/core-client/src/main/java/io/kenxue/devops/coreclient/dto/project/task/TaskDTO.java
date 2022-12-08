package io.kenxue.devops.coreclient.dto.project.task;

import io.kenxue.devops.coreclient.dto.common.command.CommonDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import io.swagger.v3.oas.annotations.media.Schema;

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
    @Schema(description = "状态")
    private String status;
    @Schema(description = "任务开始时间")
    private Date startTime;
    @Schema(description = "任务组uuid")
    private String groupUuid;
    @Schema(description = "执行人")
    private String userUuid;
    @Schema(description = "任务名称")
    private String taskName;
    @Schema(description = "任务结束时间")
    private Date endTime;
    @Schema(description = "任务详细内容（富文本）")
    private String taskDesc;
    @Schema(description = "父级任务uuid")
    private String parentUuid;
    @Schema(description = "优先级")
    private String priority;
    @Schema(description = "标签")
    private String label;
    @Schema(description = "预计工时")
    private Integer taskTime;
    @Schema(description = "子任务")
    private List<TaskDTO> children;
}
