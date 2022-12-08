package io.kenxue.devops.coreclient.dto.project.taskworkinghours;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
/**
 * 工时
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskWorkingHoursListQry extends CommonCommand {
    @Schema(description = "任务uuid")
    private String taskUuid;
    @Schema(description = "任务开始时间")
    private Date startTime;
    @Schema(description = "任务结束时间")
    private Date endTime;
    @Schema(description = "任务详细内容（富文本）")
    private String workDesc;
}
