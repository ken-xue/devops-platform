package io.kenxue.devops.coreclient.dto.pipeline.pipeline;

import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
import io.kenxue.devops.sharedataboject.pipeline.enums.PipelineTargetEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;

/**
 * 流水线
 * @author mikey
 * @date 2021-12-28 22:57:10
 */
@Data
@Accessors(chain = true)
public class PipelineExecuteCmd extends CommonCommand {
    @NotNull(message = "流水线Id不能为空")
    private Long id;
    //触发方式
    private PipelineTargetEnum targetWay;
    //手动触发时记录触发人
    private String targetUser;
}
