package io.kenxue.devops.coreclient.dto.project.task;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
@Data
@Accessors(chain = true)
public class TaskPageQry extends PageQuery {

    private TaskDTO taskDTO;

}
