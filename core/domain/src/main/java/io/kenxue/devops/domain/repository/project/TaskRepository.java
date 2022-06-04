package io.kenxue.devops.domain.repository.project;

import io.kenxue.devops.coreclient.dto.common.page.Page;
import io.kenxue.devops.coreclient.dto.project.task.TaskListQry;
import io.kenxue.devops.coreclient.dto.project.task.TaskPageQry;
import io.kenxue.devops.domain.domain.project.Task;
import java.util.List;
/**
 * 项目任务
 * @author 麦奇
 * @date 2022-06-03 23:20:11
 */
public interface TaskRepository {
    void create(Task task);
    void update(Task task);
    Task getById(Long id);
    List<Task> list(TaskListQry taskListQry);
    Page<Task> page(TaskPageQry qry);
}
