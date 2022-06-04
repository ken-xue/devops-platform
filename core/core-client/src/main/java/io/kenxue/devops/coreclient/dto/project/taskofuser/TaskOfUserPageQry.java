package io.kenxue.devops.coreclient.dto.project.taskofuser;

import io.kenxue.devops.coreclient.dto.common.command.PageQuery;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
@Data
@Accessors(chain = true)
public class TaskOfUserPageQry extends PageQuery {

    private TaskOfUserDTO taskOfUserDTO;

}
