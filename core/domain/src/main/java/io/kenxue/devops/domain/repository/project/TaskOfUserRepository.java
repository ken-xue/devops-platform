package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserListQry;
import io.kenxue.devops.coreclient.dto.project.taskofuser.TaskOfUserPageQry;
import io.kenxue.devops.domain.domain.project.TaskOfUser;
import java.util.List;
/**
 * 任务关联用户
 * @author 麦奇
 * @date 2022-06-04 00:21:17
 */
public interface TaskOfUserRepository {
    void create(TaskOfUser taskOfUser);
    void update(TaskOfUser taskOfUser);
    TaskOfUser getById(Long id);
    List<TaskOfUser> list(TaskOfUserListQry taskOfUserListQry);
    Page<TaskOfUser> page(TaskOfUserPageQry qry);
}
