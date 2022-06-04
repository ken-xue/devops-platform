package io.kenxue.devops.coreclient.dto.project.taskfile;

import lombok.Data;
import javax.validation.constraints.NotNull;
import lombok.experimental.Accessors;
import io.kenxue.devops.coreclient.dto.common.command.CommonCommand;
/**
 * 任务文件
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskFileUpdateCmd extends CommonCommand {

    @NotNull
    private TaskFileDTO taskFileDTO;
}
