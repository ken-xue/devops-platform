package io.kenxue.devops.coreclient.dto.project.taskgroup;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
@Data
@Accessors(chain = true)
public class TaskGroupPageQry extends PageQuery {

    private TaskGroupDTO taskGroupDTO;

}
