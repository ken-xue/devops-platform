package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupListQry;
import io.kenxue.devops.coreclient.dto.project.taskgroup.TaskGroupPageQry;
import io.kenxue.devops.domain.domain.project.TaskGroup;
import java.util.List;
/**
 * 任务组
 * @author 麦奇
 * @date 2022-06-03 18:11:21
 */
public interface TaskGroupRepository {
    void create(TaskGroup taskGroup);
    void update(TaskGroup taskGroup);
    TaskGroup getById(Long id);
    List<TaskGroup> list(TaskGroupListQry taskGroupListQry);
    Page<TaskGroup> page(TaskGroupPageQry qry);
}
